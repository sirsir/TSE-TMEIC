Imports System.Threading
Imports Calib.IMGLibNet.Api
Imports Calib.IMGLibNet.Def
Imports Calib.SystemLibNet.Def

Public Class ScanImg : Implements ScanCodeImpl

    Private readData As String
    Private thread As Thread
    Private decodeStop As Boolean = False

    Public Sub Connect() Implements ScanCodeImpl.Connect

        IMGDisconnect()
        IMGDeinit()

        Dim code As Integer = 0

        code = IMGInit()

        If code <> IMG_SUCCESS Then
            Throw New CodeScanException(Me.MessageFormat("IMGInit", code))
        End If

        Dim iniFilePath As String = DtxUtils.GetSystemName(IMGIni_FilePath)

        code = IMGLoadConfigFile(iniFilePath)

        If code <> IMG_SUCCESS Then
            Throw New CodeScanException(Me.MessageFormat("IMGLoadConfigFile", code))
        End If

        code = IMGConnect()

        If code <> IMG_SUCCESS Then
            Throw New CodeScanException(Me.MessageFormat("IMGConnect", code))
        End If

        decodeStop = True
        thread = New Thread(New ThreadStart(AddressOf DecodeThread))
        thread.Start()

    End Sub

    Public Sub Disconnect() Implements ScanCodeImpl.Disconnect

        decodeStop = True

        If thread IsNot Nothing Then
            thread.Abort()
        End If

        Dim code As Integer = 0

        code = IMGDisconnect()

        If code <> IMG_SUCCESS Then
            Throw New CodeScanException(Me.MessageFormat("IMGDisconnect", code))
        End If

        code = IMGDeinit()

        If code <> IMG_SUCCESS Then
            Throw New CodeScanException(Me.MessageFormat("IMGDeinit", code))
        End If

    End Sub

    Public Sub Decode() Implements ScanCodeImpl.Decode

        Dim code As Integer = 0
        Dim readTimeout As Integer = 1000
        Dim message As String = Space(1500)
        Dim codeId As String = Space(1)
        Dim aimId As String = Space(1)
        Dim symModifier As String = Space(1)
        Dim length As Integer = 0

        code = IMGWaitForDecode(readTimeout, message, codeId, aimId, symModifier, length, IntPtr.Zero)

        If code = IMG_SUCCESS Then
            readData = message
            decodeStop = True
        End If
    End Sub

    Private Sub DecodeThread()

        While (True)

            If Not decodeStop Then
                Decode()
            End If

            thread.Sleep(100)

        End While
    End Sub

    Public Sub ScanStart() Implements ScanCodeImpl.ScanStart
        readData = String.Empty
        decodeStop = False
    End Sub

    Public Sub ScanStop() Implements ScanCodeImpl.ScanStop

        decodeStop = True

        Dim code As Integer = 0

        code = IMGStopDecode()

        If code <> IMG_SUCCESS Then
            Throw New CodeScanException(Me.MessageFormat("IMGStopDecode", code))
        End If

    End Sub

    Public Function GetScanData() As String Implements ScanCodeImpl.GetScanData
        Return readData
    End Function

    Private Function MessageFormat(ByVal apiName As String, ByVal returnCode As Integer) As String
        Return String.Format("Api({0}),ReturnCode({1})", apiName, returnCode)
    End Function

End Class

Imports System.Threading
Imports Calib.OBReadLibNet.Api
Imports Calib.OBReadLibNet.Def
Imports Calib.SystemLibNet.Api
Imports Calib.SystemLibNet.Def
Imports System.Runtime.InteropServices

Public Class ScanLaser : Implements ScanCodeImpl

    Private Declare Function CreateEvent Lib "coredll.dll" ( _
        ByVal lpEventAttributes As IntPtr, ByVal bManualReset As Boolean, ByVal bInitialStatre As Boolean, ByVal lpName As String) As IntPtr

    <DllImport("coredll.dll", SetLastError:=True)> _
    Public Shared Function WaitForSingleObject(ByVal handle As IntPtr, ByVal milliseconds As Integer) As Integer
    End Function

    Private hEvent As Long

    Private readData As String
    Private thread As Thread
    Private decodeStop As Boolean = False

    Public Sub Connect() Implements ScanCodeImpl.Connect

        OBRClose()
        hEvent = CreateEvent(Nothing, False, False, "OBRScanningEvent")

        OBRLoadConfigFile()
        OBRSetDefaultSymbology()
        OBRSetVibrator(OBR_VIBON)
        OBRSetScanningKey(OBR_TRIGGERKEY_L Or OBR_TRIGGERKEY_R Or OBR_CENTERTRIGGER)
        OBRSetScanningCode(OBR_ALL)
        OBRSetBuffType(OBR_BUFOBR)
        OBRSetScanningNotification(OBR_EVENT, Nothing)

        OBROpen(Nothing, 0)

        OBRClearBuff()

        decodeStop = True
        thread = New Thread(New ThreadStart(AddressOf DecodeThread))
        thread.Start()

    End Sub

    Public Sub Disconnect() Implements ScanCodeImpl.Disconnect

        decodeStop = True

        If thread IsNot Nothing Then
            thread.Abort()
        End If

        OBRClose()

    End Sub

    Public Sub Decode() Implements ScanCodeImpl.Decode

        WaitForSingleObject(hEvent, INFINITE)

        Dim lastEventStatus As Integer

        OBRGetLastEventStatus(lastEventStatus)

        If (lastEventStatus <> OBR_SUCCESS) Then Return

        Dim leng As Byte
        Dim dwrcd As Integer
        Dim buff(1024) As Byte

        OBRGets(buff, dwrcd, leng)

        Dim ascii As System.Text.Encoding = System.Text.Encoding.GetEncoding("ascii")
        Dim message As String = ascii.GetString(buff, 0, leng)

        If message.IndexOf("*"c) = 0 Then
            message = message.Remove(0, 1)
        End If

        If message.LastIndexOf("*"c) = 0 Then
            message = message.Remove(message.Length - 1, 1)
        End If

        readData = message
        decodeStop = True

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
        OBRClearBuff()
        readData = String.Empty
        decodeStop = False
    End Sub

    Public Sub ScanStop() Implements ScanCodeImpl.ScanStop

        decodeStop = True

    End Sub

    Public Function GetScanData() As String Implements ScanCodeImpl.GetScanData
        Return readData
    End Function

    Private Function MessageFormat(ByVal apiName As String, ByVal returnCode As Integer) As String
        Return String.Format("Api({0}),ReturnCode({1})", apiName, returnCode)
    End Function

End Class

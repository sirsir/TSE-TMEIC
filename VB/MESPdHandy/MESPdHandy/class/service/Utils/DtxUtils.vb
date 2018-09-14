Imports Calib.SystemLibNet
Imports Calib.SystemLibNet.Api
Imports Calib.SystemLibNet.Def

''' <summary>
''' Casio Handy Terminal(DT-X series) Utility
''' </summary>
''' <remarks></remarks>
Public NotInheritable Class DtxUtils

    Private Sub New()
    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="keyId"></param>
    ''' <param name="changeKey"></param>
    ''' <remarks></remarks>
    Public Shared Sub SetUserDefineKey(ByVal keyId As Integer, ByVal changeKey As Integer)

        Dim keyChange(15) As Int32

        For i = 0 To 15
            keyChange(i) = 0
        Next

        keyChange(0) = changeKey

        SysSetUserDefineKey(KEY_MODE_NUM, keyId, keyChange)
        SysSetUserDefineKeyState(True)
        SysSetUserDefineKey(KEY_MODE_ALPHA, keyId, keyChange)
        SysSetUserDefineKeyState(True)
        SysSetUserDefineKey(KEY_MODE_ALPHAS, keyId, keyChange)
        SysSetUserDefineKeyState(True)

    End Sub

    Public Shared Sub InputModeString()

        Dim setEnableKeyMode As Integer = _
            SYS_ENABLE_KEYMODE_NUM _
            Or SYS_ENABLE_KEYMODE_HIRA _
            Or SYS_ENABLE_KEYMODE_KANA _
            Or SYS_ENABLE_KEYMODE_ALPHA _
            Or SYS_ENABLE_KEYMODE_ALPHAS

        InputMode(setEnableKeyMode)
    End Sub

    Public Shared Sub InputModeAlphaNumeric()

        Dim setEnableKeyMode As Integer = _
            SYS_ENABLE_KEYMODE_NUM _
            Or SYS_ENABLE_KEYMODE_ALPHA _
            Or SYS_ENABLE_KEYMODE_ALPHAS

        InputMode(setEnableKeyMode)
    End Sub

    Public Shared Sub InputModeAlpha()

        Dim setEnableKeyMode As Integer = _
            SYS_ENABLE_KEYMODE_ALPHA _
            Or SYS_ENABLE_KEYMODE_ALPHAS

        InputMode(setEnableKeyMode)
    End Sub

    Public Shared Sub InputModeNumeric()

        Dim setEnableKeyMode As Integer = _
            SYS_ENABLE_KEYMODE_NUM

        InputMode(setEnableKeyMode)
    End Sub

    Private Shared Sub InputMode(ByVal setEnableKeyMode As Integer)

        Dim nowEnableKeyMode As Integer = SysGetEnableKeyMode()

        If setEnableKeyMode <> nowEnableKeyMode Then

            SysSetEnableKeyMode(setEnableKeyMode)
            SysSetInputMode(INPUT_NORMAL)
        End If
    End Sub

    Public Shared Function GetInputModeName() As String

        Dim mode As Integer = SysGetInputMode()
        Dim name As String = String.Empty

        Select Case mode
            Case INPUT_NORMAL, INPUT_LOCK_NUM
                name = "1"
            Case INPUT_LOCK_HIRA
                name = "あ"
            Case INPUT_LOCK_KANA
                name = "ア"
            Case INPUT_LOCK_ALPHA
                name = "A"
            Case INPUT_LOCK_ALPHAS
                name = "a"
        End Select

        Return name.PadLeft(2)

    End Function

    Public Shared Function GetSystemName(ByVal valueId As Integer) As String

        Dim value(79) As Char

        SysGetSystemName(valueId, value)

        If value(0) = Nothing Then
            Return String.Empty
        End If

        Return New String(value)

    End Function

End Class

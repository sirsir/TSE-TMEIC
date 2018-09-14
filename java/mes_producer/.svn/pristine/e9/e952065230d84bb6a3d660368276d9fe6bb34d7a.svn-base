Option Explicit

Dim logoutFilePath

Sub SetLogFilePath(strTargetFile)
    logoutFilePath = strTargetFile
End Sub

Sub AddLog(strMessage)
    On Error Resume Next
    
    Const ForAppending = 8 
    Dim fso, fi
    Set fso = CreateObject("Scripting.FileSystemObject")
    
    'File open or create
    Set fi = fso.OpenTextFile(logoutFilePath, ForAppending, true)
    strMessage = Date() & " " & Time() & ": " & strMessage
    fi.WriteLine (strMessage) 
    Set fi = Nothing
End Sub
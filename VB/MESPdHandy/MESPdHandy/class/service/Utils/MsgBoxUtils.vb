Imports Microsoft.VisualBasic

''' <summary>
''' MsgBox Utility
''' </summary>
''' <remarks></remarks>
Public NotInheritable Class MsgBoxUtils

    Private Sub New()
    End Sub

    ''' <summary>
    ''' Warning message box show 
    ''' </summary>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function Warning(ByVal message As String) As Integer
        Return MsgBox(message, MsgBoxStyle.Critical, "WARNING")
    End Function

    ''' <summary>
    ''' Error message box show 
    ''' </summary>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function Err(ByVal message As String) As Integer
        Return MsgBox(message, MsgBoxStyle.Critical, "ERROR")
    End Function

    ''' <summary>
    ''' YesNo message box show 
    ''' </summary>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function YesNo(ByVal message As String) As Integer
        Return MsgBox(message, MsgBoxStyle.YesNo, "")
    End Function

End Class

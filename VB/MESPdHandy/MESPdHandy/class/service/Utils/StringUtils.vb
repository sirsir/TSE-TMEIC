''' <summary>
''' String Utility
''' </summary>
''' <remarks></remarks>
Public NotInheritable Class StringUtils

    Private Sub New()
    End Sub

    Public Shared Function TruncateLength(ByVal val As String, ByVal len As Integer) As String

        If String.IsNullOrEmpty(val) Then Return val
        If val.Length <= len Then Return val

        Return val.Substring(0, len)

    End Function

End Class

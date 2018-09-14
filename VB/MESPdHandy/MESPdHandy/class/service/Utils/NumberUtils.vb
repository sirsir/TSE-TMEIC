''' <summary>
''' Number Utility
''' </summary>
''' <remarks></remarks>
Public NotInheritable Class NumberUtils

    Private Sub New()
    End Sub

    ''' <summary>
    ''' A string representation of a number, same to convert to an equivalent 32-bit signed integer.
    ''' </summary>
    ''' <param name="val"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function Parse(ByVal val As String) As Integer

        If String.IsNullOrEmpty(val) Then Return 0

        Return Integer.Parse(val)

    End Function

    ''' <summary>
    ''' A string representation of a number, same to convert to an equivalent 32-bit signed integer.
    ''' </summary>
    ''' <param name="val"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function IsNumber(ByVal val As String) As Boolean

        Try
            Decimal.Parse(val)
        Catch ex As Exception
            Return False
        End Try

        Return True
    End Function

End Class

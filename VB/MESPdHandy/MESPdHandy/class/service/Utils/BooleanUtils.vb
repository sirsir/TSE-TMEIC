''' <summary>
''' Boolean utility
''' </summary>
''' <remarks></remarks>
Public NotInheritable Class BooleanUtils

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="value"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function Parse(ByVal value As String) As Boolean

        If String.IsNullOrEmpty(value) Then Return False

        Return Boolean.Parse(value)

    End Function

End Class

''' <summary>
''' Process product status
''' </summary>
''' <remarks></remarks>
Public MustInherit Class ProcessProductStatus

    Public Const NONE As Integer = 0
    Public Const START As Integer = 1
    Public Const COMPLETE As Integer = 2

    ''' <summary>
    ''' Status name list
    ''' </summary>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function NameList() As Dictionary(Of Integer, String)

        Dim name As New Dictionary(Of Integer, String)

        name.Add(NONE, My.Resources.ProcessProductStatusNone)
        name.Add(START, My.Resources.ProcessProductStatusStart)
        name.Add(COMPLETE, My.Resources.ProcessProductStatusComplete)

        Return name

    End Function

    ''' <summary>
    ''' Get Status name
    ''' </summary>
    ''' <param name="status"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function GetName(ByVal status As Integer) As String

        Dim name As Dictionary(Of Integer, String) = NameList()

        If Not name.ContainsKey(status) Then Return String.Empty

        Return name.Item(status)

    End Function

End Class

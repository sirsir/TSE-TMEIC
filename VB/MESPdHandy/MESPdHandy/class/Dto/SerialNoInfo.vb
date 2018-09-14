''' <summary>
''' SerialNo information
''' </summary>
''' <remarks></remarks>
Public Class SerialNoInfo

    ''' <summary>SerialNo</summary>
    Public SerialNo As String

    ''' <summary>Work Status</summary>
    Public Status As Integer

    ''' <summary>Material</summary>
    Public Materials As New List(Of MaterialInfo)

    ''' <summary>SpecProduct</summary>
    Public SpecProducts As New List(Of SpecInfo)

End Class

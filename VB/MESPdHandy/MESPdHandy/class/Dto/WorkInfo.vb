''' <summary>
''' Product information
''' </summary>
''' <remarks></remarks>
Public Class WorkInfo

    ''' <summary>ProductName</summary>
    Public ProductName As String

    ''' <summary>ProductPlanNo</summary>
    Public ProductPlanNo As String

    ''' <summary>ProcessName</summary>
    Public ProcessName As String

    ''' <summary>Barcode</summary>
    Public Barcode As String

    ''' <summary>ProductStatus</summary>
    Public Status As Integer

    ''' <summary>SpecProduct</summary>
    Public SpecProducts As New List(Of SpecInfo)

End Class

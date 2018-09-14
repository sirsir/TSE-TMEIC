''' <summary>
''' Spec information
''' </summary>
''' <remarks></remarks>
Public Class SpecInfo
    Inherits DisplayOrder

    ''' <summary>SpecComponentNo</summary>
    Public SpecComponentNo As Integer

    ''' <summary>SpecId</summary>
    Public SpecId As String

    ''' <summary>SpecName</summary>
    Public SpecName As String

    ''' <summary>SpecAttributeId</summary>
    Public SpecAttributeId As String

    ''' <summary>SpecAttributeName</summary>
    Public SpecAttributeName As String

    ''' <summary>SpecParts01</summary>
    Public SpecParts01 As String

    ''' <summary>SpecParts02</summary>
    Public SpecParts02 As String

    ''' <summary>SpecParts03</summary>
    Public SpecParts03 As String

    ''' <summary>SpecParts04</summary>
    Public SpecParts04 As String

    ''' <summary>SpecParts05</summary>
    Public SpecParts05 As String

    ''' <summary>SpecParts06</summary>
    Public SpecParts06 As String

    ''' <summary>SpecParts07</summary>
    Public SpecParts07 As String

    ''' <summary>SpecParts08</summary>
    Public SpecParts08 As String

    ''' <summary>SpecParts09</summary>
    Public SpecParts09 As String

    ''' <summary>SpecParts10</summary>
    Public SpecParts10 As String

    ''' <summary>InputValue</summary>
    Public InputValue As String

    ''' <summary>All SpecParts</summary>
    Public Function GetSpecParts() As List(Of String)

        Dim list As New List(Of String)
        list.Add(SpecParts01)
        list.Add(SpecParts02)
        list.Add(SpecParts03)
        list.Add(SpecParts04)
        list.Add(SpecParts05)
        list.Add(SpecParts06)
        list.Add(SpecParts07)
        list.Add(SpecParts08)
        list.Add(SpecParts09)
        list.Add(SpecParts10)

        Return list

    End Function

End Class

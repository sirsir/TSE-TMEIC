Imports System.IO
Imports System.Reflection

''' <summary>
''' Compact framework extension utility
''' </summary>
''' <remarks></remarks>
Public NotInheritable Class CfUtils

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="form"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function ActiveControl(ByVal form As Form) As Control
        Return GetFocusedControl(form)
    End Function

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="parent"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Private Shared Function GetFocusedControl(ByRef parent As Control) As Control

        If (parent.Focused) Then
            Return parent
        End If

        For Each ctrl As Control In parent.Controls
            Dim temp As Control = GetFocusedControl(ctrl)
            If (Not (temp Is Nothing)) Then
                Return temp
            End If
        Next

        Return Nothing
    End Function

    ''' <summary>
    ''' Get current directory
    ''' </summary>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function GetCurrentDirectory() As String
        Return Path.GetDirectoryName(Assembly.GetExecutingAssembly().GetName().CodeBase)
    End Function

    ''' <summary>
    ''' Escape of text properties
    ''' </summary>
    ''' <param name="text"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function TextEscape(ByVal text As String) As String

        If (String.IsNullOrEmpty(text)) Then Return String.Empty

        Return text.Replace("&", "&&")

    End Function

End Class

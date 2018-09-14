Public Class CfButton
    Inherits Windows.Forms.Button

    ''' <summary>UseMnemonic</summary>
    Private _UseMnemonic As Boolean

    ''' <summary>Highlight</summary>
    Private _Highlight As Boolean = False

    Protected Overrides Sub OnPaint(ByVal e As System.Windows.Forms.PaintEventArgs)
        MyBase.OnPaint(e)
    End Sub

    Property UseMnemonic() As Boolean
        Get
            Return _UseMnemonic
        End Get
        Set(ByVal value As Boolean)
            _UseMnemonic = value
        End Set
    End Property

    Property Highlight() As Boolean
        Get
            Return _Highlight
        End Get
        Set(ByVal value As Boolean)
            _Highlight = value
        End Set
    End Property

    Overrides Property Text() As String
        Get
            Dim val As String = MyBase.Text

            If Not _UseMnemonic Then
                val = Me.TextUnescape(val)
            End If

            Return val
        End Get
        Set(ByVal value As String)
            Dim val As String = value

            If Not _UseMnemonic Then
                val = Me.TextEscape(value)
            End If

            MyBase.Text = val
        End Set
    End Property

    Protected Sub CfGotFocus(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Me.GotFocus

        If _Highlight Then
            If BackColor = SystemColors.Control Then
                BackColor = Color.Aqua
            End If
        End If

    End Sub

    Protected Sub CfLostFocus(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Me.LostFocus

        If _Highlight Then
            If BackColor = Color.Aqua Then
                BackColor = SystemColors.Control
            End If
        End If

    End Sub

    ''' <summary>
    ''' Escape of text properties
    ''' </summary>
    ''' <param name="text"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Private Function TextEscape(ByVal text As String) As String

        If (String.IsNullOrEmpty(text)) Then Return text

        Return text.Replace("&", "&&")

    End Function

    ''' <summary>
    ''' Unescape of text properties
    ''' </summary>
    ''' <param name="text"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Private Function TextUnescape(ByVal text As String) As String

        If (String.IsNullOrEmpty(text)) Then Return text

        Return text.Replace("&&", "&")

    End Function

End Class

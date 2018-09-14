Public Class frmSpecButton
    Inherits frmSpec

    ''' <summary>
    ''' Form Load event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub frmSpecButton_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        Try
            Dim spec As SpecInfo = DisplayOrderUtils.SelectOrder(Me.AppData.SpecInfo(), Me.AppData.NowDisplayOrder)

            Dim specParts = From p In spec.GetSpecParts() _
                             Where Not String.IsNullOrEmpty(p) _
                             Select p

            Me.btnInputValue1.Hide()
            Me.btnInputValue2.Hide()
            Me.btnInputValue3.Hide()

            Dim count As Integer = 1

            For Each specPart As String In specParts

                Select Case count
                    Case 1
                        Me.btnInputValue1.Text = specPart
                        Me.btnInputValue1.Show()
                    Case 2
                        Me.btnInputValue2.Text = specPart
                        Me.btnInputValue2.Show()
                    Case 3
                        Me.btnInputValue3.Text = specPart
                        Me.btnInputValue3.Show()
                End Select

                count += 1
            Next

            Me.ToggleBtnInputValue(spec.InputValue)

        Catch ex As Exception
            'MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub btnInputValue1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnInputValue1.Click

        Try
            Me.ToggleBtnInputValue(btnInputValue1.Text)
        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub btnInputValue2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnInputValue2.Click

        Try
            Me.ToggleBtnInputValue(btnInputValue2.Text)
        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub btnInputValue3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnInputValue3.Click

        Try
            Me.ToggleBtnInputValue(btnInputValue3.Text)
        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <remarks></remarks>
    Private Sub ToggleBtnInputValue(ByVal value As String)

        Dim spec As SpecInfo = DisplayOrderUtils.SelectOrder(Me.AppData.SpecInfo(), Me.AppData.NowDisplayOrder)
        spec.InputValue = value

        Me.btnInputValue1.BackColor = SystemColors.Control
        Me.btnInputValue2.BackColor = SystemColors.Control
        Me.btnInputValue3.BackColor = SystemColors.Control

        Select Case value
            Case Me.btnInputValue1.Text
                Me.btnInputValue1.BackColor = Color.LimeGreen

            Case Me.btnInputValue2.Text
                Me.btnInputValue2.BackColor = Color.LimeGreen

            Case Me.btnInputValue3.Text
                Me.btnInputValue3.BackColor = Color.LimeGreen
        End Select
    End Sub

End Class
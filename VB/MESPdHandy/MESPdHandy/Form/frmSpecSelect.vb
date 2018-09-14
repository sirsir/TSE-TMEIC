Public Class frmSpecSelect
    Inherits frmSpec

    ''' <summary>
    ''' Form Load event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub frmSpecSelect_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        Try
            Dim spec As SpecInfo = DisplayOrderUtils.SelectOrder(Me.AppData.SpecInfo(), Me.AppData.NowDisplayOrder)

            Me.InitCmbInput(Me.cmbInputValue, spec)

            Me.cmbInputValue.Text = spec.InputValue

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' InputValue of Selected Index Changed event
    ''' record the input value
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub cmbInputValue_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmbInputValue.SelectedIndexChanged

        Try
            Dim spec As SpecInfo = DisplayOrderUtils.SelectOrder(Me.AppData.SpecInfo(), Me.AppData.NowDisplayOrder)

            spec.InputValue = Me.cmbInputValue.Text

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' Initialize ComboBox
    ''' </summary>
    ''' <param name="cmb"></param>
    ''' <param name="spec"></param>
    ''' <remarks></remarks>
    Private Sub InitCmbInput(ByVal cmb As ComboBox, ByVal spec As SpecInfo)

        cmb.SelectedIndex = -1
        cmb.Items.Clear()

        For Each specParts As String In spec.GetSpecParts()

            If String.IsNullOrEmpty(specParts) Then Continue For

            cmb.Items.Add(specParts)
        Next
    End Sub

End Class
Public Class frmSpecInput
    Inherits frmSpec

    ''' <summary>
    ''' Form Load event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub frmSpecInput_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        Try
            Me.scan = ScanCodeFactory.ScanCode()
            Me.scan.Connect()

            Dim spec As SpecInfo = DisplayOrderUtils.SelectOrder(Me.AppData.SpecInfo(), Me.AppData.NowDisplayOrder)

            Me.txtInputValue.Text = spec.InputValue

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' Form Closing event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub frmSpecInput_Closing(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Closing

        Try
            Me.scan.Disconnect()
        Catch ex As CodeScanException
            MsgBoxUtils.Err("SCAN ERROR")
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
    Private Sub txtInputValue_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles txtInputValue.TextChanged

        Try
            Dim spec As SpecInfo = DisplayOrderUtils.SelectOrder(Me.AppData.SpecInfo(), Me.AppData.NowDisplayOrder)

            spec.InputValue = Me.txtInputValue.Text

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
    Private Sub txt_GotFocus(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles txtInputValue.GotFocus

        Try
            Dim spec As SpecInfo = DisplayOrderUtils.SelectOrder(Me.AppData.SpecInfo(), Me.AppData.NowDisplayOrder)

            If spec.SpecAttributeId = SpecAttributeId.NUMBER Then
                Me.InputModeNumeric()
            Else
                Me.InputModeString()
            End If

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' Get the scan data
    ''' </summary>
    ''' <param name="data"></param>
    ''' <remarks></remarks>
    Protected Friend Overrides Sub GetScanCode(ByVal data As String)

        Try
            If String.IsNullOrEmpty(data) Then Return

            Dim spec As SpecInfo = DisplayOrderUtils.SelectOrder(Me.AppData.SpecInfo(), Me.AppData.NowDisplayOrder)

            If spec.SpecAttributeId = SpecAttributeId.NUMBER Then
                If Not NumberUtils.IsNumber(data) Then Return
            End If

            Me.txtInputValue.Text = StringUtils.TruncateLength(data, Me.txtInputValue.MaxLength)

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

End Class
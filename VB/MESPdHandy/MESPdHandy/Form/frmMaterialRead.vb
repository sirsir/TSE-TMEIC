Imports Calib.SystemLibNet.Def
Imports MESPdHandy.Service

''' <summary>
''' 
''' </summary>
''' <remarks></remarks>
Public Class frmMaterialRead
    Inherits frmMaterial

    ''' <summary>
    ''' Form Load event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub frmMaterialRead_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        Try
            scan = ScanCodeFactory.ScanCode()
            scan.Connect()

            Me.txtMaterialCode.Focus()
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
    Private Sub frmMaterialRead_Closing(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Closing

        Try
            scan.Disconnect()
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
    Protected Overrides Sub btnF1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

        Try
            Me.MoveNextForm(New frmInputWork())
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
    Protected Overrides Sub btnF2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

        Try
            Dim material As MaterialInfo = DisplayOrderUtils.PrevOrder(Me.AppData.SerialNoInfo.Materials, Me.AppData.NowDisplayOrder)
            Me.AppData.NowDisplayOrder = material.DisplayOrder

            Me.MoveNextForm(New frmMaterialRead())

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
    Protected Overrides Sub btnF3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

        Try
            Dim material As MaterialInfo = DisplayOrderUtils.NextOrder(Me.AppData.SerialNoInfo.Materials, Me.AppData.NowDisplayOrder)
            Me.AppData.NowDisplayOrder = material.DisplayOrder

            Me.MoveNextForm(New frmMaterialRead())

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
    Private Sub btnEnter_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnEnter.Click

        Try
            Me.ReadMaterialEnter()

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <remarks></remarks>
    Private Sub ReadMaterialEnter()

        Dim material As MaterialInfo = DisplayOrderUtils.SelectOrder(Me.AppData.SerialNoInfo.Materials, Me.AppData.NowDisplayOrder)
        Dim materialId As String = Me.txtMaterialCode.Text.Trim()

        If materialId <> material.MaterialId.Trim() Then

            Dim productId As String = ProductService.InterimProductId(materialId)

            If productId <> material.MaterialId.Trim() Then

                MsgBoxUtils.Warning(My.Resources.ErrorMaterialIncorrect())
                Return
            End If
        End If

        Me.MoveNextForm(New frmMaterialInput())

    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub txt_GotFocus(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles txtMaterialCode.GotFocus

        Try
            Me.InputModeAlphaNumeric()
            Me.txtMaterialCode.SelectAll()

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="data"></param>
    ''' <remarks></remarks>
    Protected Friend Overrides Sub GetScanCode(ByVal data As String)

        Try
            If String.IsNullOrEmpty(data) Then Return

            Me.txtMaterialCode.Text = data
            Me.ReadMaterialEnter()

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

End Class
Imports System.Net
Imports MESPdHandy.Service

''' <summary>
''' 
''' </summary>
''' <remarks></remarks>
Public Class frmMaterialInput
    Inherits frmMaterial

    ''' <summary>
    ''' Form Load event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub frmMaterialInput_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        Try
            If AppDomain.CurrentDomain.FriendlyName = "DefaultDomain" Then Return

            Dim material As MaterialInfo = DisplayOrderUtils.SelectOrder(Me.AppData.SerialNoInfo.Materials, Me.AppData.NowDisplayOrder)

            Me.lblPlanQtyValue.Text = String.Format("{0}{1}", material.MaterialQtyPlan, material.MaterialUnit)

            If material.MaterialQty IsNot Nothing Then
                Me.txtResultQty.Text = material.MaterialQty
            End If

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
            Me.MoveNextForm(New frmMaterialRead())
        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' F4 button click event
    ''' Register the material, move to the next form
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Protected Overrides Sub btnF4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

        Try
            Dim serialNo As String = Me.AppData.SerialNoInfo.SerialNo
            Dim process As ProcessInfo = Me.AppData.Process

            Dim material As MaterialInfo = DisplayOrderUtils.SelectOrder(Me.AppData.SerialNoInfo.Materials, Me.AppData.NowDisplayOrder)

            ProductService.MaterialRegister(process.ProductPlanNo, process.ProcessComponentNo, serialNo, material)

            If Me.AppData.IsMaterialInputCompletion() Then

                Me.AppData.NowSpecType = MESPdHandy.AppData.SPEC_TYPE.PRODUCT

                Dim spec As SpecInfo = DisplayOrderUtils.FirstOrder(Me.AppData.SerialNoInfo.SpecProducts)
                Me.AppData.NowDisplayOrder = spec.DisplayOrder

                Me.MoveNextForm(SpecFormUtils.GetSpecForm(spec.SpecAttributeId))

                Return
            End If

            Dim nextMaterial As MaterialInfo = DisplayOrderUtils.NextOrder(Me.AppData.SerialNoInfo.Materials, Me.AppData.NowDisplayOrder)
            Me.AppData.NowDisplayOrder = nextMaterial.DisplayOrder

            Me.MoveNextForm(New frmMaterialRead())

        Catch ex As MesPdException
            MsgBoxUtils.Err(ex.Message)
        Catch ex As WebException
            MsgBoxUtils.Err("NETWORK ERROR")
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
    Private Sub txt_GotFocus(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles txtResultQty.GotFocus

        Try
            Me.InputModeNumeric()
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
    Private Sub txtResultQty_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles txtResultQty.TextChanged

        Try
            Dim material As MaterialInfo = DisplayOrderUtils.SelectOrder(Me.AppData.SerialNoInfo.Materials, Me.AppData.NowDisplayOrder)

            material.MaterialQty = NumberUtils.Parse(Me.txtResultQty.Text)

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

End Class
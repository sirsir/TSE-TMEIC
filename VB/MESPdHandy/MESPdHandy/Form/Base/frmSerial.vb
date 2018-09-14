Imports System.Net
Imports MESPdHandy.Service

''' <summary>
''' 
''' </summary>
''' <remarks></remarks>
Public Class frmSerial
    Inherits frmBase

    ''' <summary>
    ''' Form Load event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub frmSerial_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        Try
            If AppDomain.CurrentDomain.FriendlyName = "DefaultDomain" Then Return

            Me.RefreshProcessInfo()
        Catch ex As WebException
            MsgBoxUtils.Err("NETWORK ERROR")
        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <remarks></remarks>
    Private Sub RefreshProcessInfo()

        Dim process As ProcessInfo = Me.AppData.Process

        Me.lblProcessName.Text = process.ProcessName
        Me.lblProductPlanNo.Text = process.ProductPlanNo
        Me.lblProductName.Text = process.ProductName
        Me.lblSerialNumberVal.Text = Me.AppData.SerialNoInfo.SerialNo
    
        'For TextAlign does not work, adjust the blank
        Me.lblCount.Text = String.Format("{0}/{1}", process.ResultQty, process.PlanQty).PadLeft(13)
    End Sub

End Class
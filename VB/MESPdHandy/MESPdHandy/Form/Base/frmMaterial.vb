''' <summary>
''' 
''' </summary>
''' <remarks></remarks>
Public Class frmMaterial
    Inherits frmSerial

    ''' <summary>
    ''' Form Load event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub frmMaterial_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        Try
            If AppDomain.CurrentDomain.FriendlyName = "DefaultDomain" Then Return

            Dim material As MaterialInfo = DisplayOrderUtils.SelectOrder(Me.AppData.SerialNoInfo.Materials, Me.AppData.NowDisplayOrder)

            Me.lblMaterialCodeVal.Text = material.MaterialId
            Me.lblMaterialName.Text = material.MaterialName

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

End Class
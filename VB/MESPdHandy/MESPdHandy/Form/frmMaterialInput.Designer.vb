<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Public Class frmMaterialInput
    Inherits MESPdHandy.frmMaterial

    'フォームがコンポーネントの一覧をクリーンアップするために dispose をオーバーライドします。
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        If disposing AndAlso components IsNot Nothing Then
            components.Dispose()
        End If
        MyBase.Dispose(disposing)
    End Sub

    'Windows フォーム デザイナで必要です。
    Private components As System.ComponentModel.IContainer

    'メモ: 以下のプロシージャは Windows フォーム デザイナで必要です。
    'Windows フォーム デザイナを使用して変更できます。  
    'コード エディタを使って変更しないでください。
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(frmMaterialInput))
        Me.lblPlanQty = New MESPdControl.CfLabel
        Me.lblPlanQtyValue = New MESPdControl.CfLabel
        Me.lblResultQty = New MESPdControl.CfLabel
        Me.txtResultQty = New System.Windows.Forms.TextBox
        Me.SuspendLayout()
        '
        'btnF1
        '
        resources.ApplyResources(Me.btnF1, "btnF1")
        '
        'btnF2
        '
        resources.ApplyResources(Me.btnF2, "btnF2")
        '
        'btnF3
        '
        resources.ApplyResources(Me.btnF3, "btnF3")
        '
        'btnF4
        '
        resources.ApplyResources(Me.btnF4, "btnF4")
        '
        'lblTitle
        '
        resources.ApplyResources(Me.lblTitle, "lblTitle")
        '
        'btnClose
        '
        resources.ApplyResources(Me.btnClose, "btnClose")
        '
        'btnF5
        '
        resources.ApplyResources(Me.btnF5, "btnF5")
        '
        'lblPlanQty
        '
        resources.ApplyResources(Me.lblPlanQty, "lblPlanQty")
        Me.lblPlanQty.Name = "lblPlanQty"
        Me.lblPlanQty.UseMnemonic = False
        '
        'lblPlanQtyValue
        '
        resources.ApplyResources(Me.lblPlanQtyValue, "lblPlanQtyValue")
        Me.lblPlanQtyValue.BackColor = System.Drawing.Color.Wheat
        Me.lblPlanQtyValue.Name = "lblPlanQtyValue"
        Me.lblPlanQtyValue.UseMnemonic = False
        '
        'lblResultQty
        '
        resources.ApplyResources(Me.lblResultQty, "lblResultQty")
        Me.lblResultQty.Name = "lblResultQty"
        Me.lblResultQty.UseMnemonic = False
        '
        'txtResultQty
        '
        resources.ApplyResources(Me.txtResultQty, "txtResultQty")
        Me.txtResultQty.Name = "txtResultQty"
        '
        'frmMaterialInput
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(96.0!, 96.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi
        resources.ApplyResources(Me, "$this")
        Me.Controls.Add(Me.txtResultQty)
        Me.Controls.Add(Me.lblResultQty)
        Me.Controls.Add(Me.lblPlanQty)
        Me.Controls.Add(Me.lblPlanQtyValue)
        Me.Name = "frmMaterialInput"
        Me.Controls.SetChildIndex(Me.btnF5, 0)
        Me.Controls.SetChildIndex(Me.btnF1, 0)
        Me.Controls.SetChildIndex(Me.btnF2, 0)
        Me.Controls.SetChildIndex(Me.btnF3, 0)
        Me.Controls.SetChildIndex(Me.btnF4, 0)
        Me.Controls.SetChildIndex(Me.lblPlanQtyValue, 0)
        Me.Controls.SetChildIndex(Me.lblPlanQty, 0)
        Me.Controls.SetChildIndex(Me.lblResultQty, 0)
        Me.Controls.SetChildIndex(Me.txtResultQty, 0)
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents lblPlanQty As MESPdControl.CfLabel
    Friend WithEvents lblPlanQtyValue As MESPdControl.CfLabel
    Friend WithEvents lblResultQty As MESPdControl.CfLabel
    Friend WithEvents txtResultQty As System.Windows.Forms.TextBox
End Class

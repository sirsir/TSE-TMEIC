<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Public Class frmMaterial
    Inherits MESPdHandy.frmSerial

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
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(frmMaterial))
        Me.lblMaterialName = New MESPdControl.CfLabel
        Me.lblMaterialCode = New MESPdControl.CfLabel
        Me.lblMaterialCodeVal = New MESPdControl.CfLabel
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
        'lblMaterialName
        '
        Me.lblMaterialName.BackColor = System.Drawing.Color.Wheat
        resources.ApplyResources(Me.lblMaterialName, "lblMaterialName")
        Me.lblMaterialName.ForeColor = System.Drawing.Color.DarkBlue
        Me.lblMaterialName.Name = "lblMaterialName"
        Me.lblMaterialName.UseMnemonic = False
        '
        'lblMaterialCode
        '
        resources.ApplyResources(Me.lblMaterialCode, "lblMaterialCode")
        Me.lblMaterialCode.Name = "lblMaterialCode"
        Me.lblMaterialCode.UseMnemonic = False
        '
        'lblMaterialCodeVal
        '
        Me.lblMaterialCodeVal.BackColor = System.Drawing.Color.Wheat
        resources.ApplyResources(Me.lblMaterialCodeVal, "lblMaterialCodeVal")
        Me.lblMaterialCodeVal.Name = "lblMaterialCodeVal"
        Me.lblMaterialCodeVal.UseMnemonic = False
        '
        'frmMaterial
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(96.0!, 96.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi
        resources.ApplyResources(Me, "$this")
        Me.Controls.Add(Me.lblMaterialCode)
        Me.Controls.Add(Me.lblMaterialCodeVal)
        Me.Controls.Add(Me.lblMaterialName)
        Me.Name = "frmMaterial"
        Me.Controls.SetChildIndex(Me.btnF5, 0)
        Me.Controls.SetChildIndex(Me.btnF1, 0)
        Me.Controls.SetChildIndex(Me.btnF2, 0)
        Me.Controls.SetChildIndex(Me.btnF3, 0)
        Me.Controls.SetChildIndex(Me.btnF4, 0)
        Me.Controls.SetChildIndex(Me.lblMaterialName, 0)
        Me.Controls.SetChildIndex(Me.lblMaterialCodeVal, 0)
        Me.Controls.SetChildIndex(Me.lblMaterialCode, 0)
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents lblMaterialName As MESPdControl.CfLabel
    Friend WithEvents lblMaterialCode As MESPdControl.CfLabel
    Friend WithEvents lblMaterialCodeVal As MESPdControl.CfLabel
End Class

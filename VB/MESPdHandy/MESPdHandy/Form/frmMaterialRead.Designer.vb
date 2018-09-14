<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Public Class frmMaterialRead
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
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(frmMaterialRead))
        Me.lblMessage = New MESPdControl.CfLabel
        Me.txtMaterialCode = New System.Windows.Forms.TextBox
        Me.btnEnter = New MESPdControl.CfButton
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
        'lblMessage
        '
        resources.ApplyResources(Me.lblMessage, "lblMessage")
        Me.lblMessage.Name = "lblMessage"
        Me.lblMessage.UseMnemonic = False
        '
        'txtMaterialCode
        '
        resources.ApplyResources(Me.txtMaterialCode, "txtMaterialCode")
        Me.txtMaterialCode.Name = "txtMaterialCode"
        '
        'btnEnter
        '
        resources.ApplyResources(Me.btnEnter, "btnEnter")
        Me.btnEnter.Highlight = True
        Me.btnEnter.Name = "btnEnter"
        Me.btnEnter.UseMnemonic = False
        '
        'frmMaterialRead
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(96.0!, 96.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi
        resources.ApplyResources(Me, "$this")
        Me.Controls.Add(Me.btnEnter)
        Me.Controls.Add(Me.txtMaterialCode)
        Me.Controls.Add(Me.lblMessage)
        Me.Name = "frmMaterialRead"
        Me.Controls.SetChildIndex(Me.btnF5, 0)
        Me.Controls.SetChildIndex(Me.btnF1, 0)
        Me.Controls.SetChildIndex(Me.btnF2, 0)
        Me.Controls.SetChildIndex(Me.btnF3, 0)
        Me.Controls.SetChildIndex(Me.btnF4, 0)
        Me.Controls.SetChildIndex(Me.lblMessage, 0)
        Me.Controls.SetChildIndex(Me.txtMaterialCode, 0)
        Me.Controls.SetChildIndex(Me.btnEnter, 0)
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents lblMessage As MESPdControl.CfLabel
    Friend WithEvents txtMaterialCode As System.Windows.Forms.TextBox
    Friend WithEvents btnEnter As MESPdControl.CfButton
End Class

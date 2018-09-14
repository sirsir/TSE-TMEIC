<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Public Class frmSpecButton
    Inherits MESPdHandy.frmSpec

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
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(frmSpecButton))
        Me.lblMessage = New MESPdControl.CfLabel
        Me.btnInputValue1 = New MESPdControl.CfButton
        Me.btnInputValue2 = New MESPdControl.CfButton
        Me.btnInputValue3 = New MESPdControl.CfButton
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
        'btnInputValue1
        '
        resources.ApplyResources(Me.btnInputValue1, "btnInputValue1")
        Me.btnInputValue1.Highlight = True
        Me.btnInputValue1.Name = "btnInputValue1"
        Me.btnInputValue1.UseMnemonic = False
        '
        'btnInputValue2
        '
        resources.ApplyResources(Me.btnInputValue2, "btnInputValue2")
        Me.btnInputValue2.Highlight = True
        Me.btnInputValue2.Name = "btnInputValue2"
        Me.btnInputValue2.UseMnemonic = False
        '
        'btnInputValue3
        '
        resources.ApplyResources(Me.btnInputValue3, "btnInputValue3")
        Me.btnInputValue3.Highlight = True
        Me.btnInputValue3.Name = "btnInputValue3"
        Me.btnInputValue3.UseMnemonic = False
        '
        'frmSpecButton
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(96.0!, 96.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi
        resources.ApplyResources(Me, "$this")
        Me.Controls.Add(Me.btnInputValue3)
        Me.Controls.Add(Me.btnInputValue2)
        Me.Controls.Add(Me.btnInputValue1)
        Me.Controls.Add(Me.lblMessage)
        Me.Name = "frmSpecButton"
        Me.Controls.SetChildIndex(Me.btnF5, 0)
        Me.Controls.SetChildIndex(Me.btnF1, 0)
        Me.Controls.SetChildIndex(Me.btnF2, 0)
        Me.Controls.SetChildIndex(Me.btnF3, 0)
        Me.Controls.SetChildIndex(Me.btnF4, 0)
        Me.Controls.SetChildIndex(Me.lblMessage, 0)
        Me.Controls.SetChildIndex(Me.btnInputValue1, 0)
        Me.Controls.SetChildIndex(Me.btnInputValue2, 0)
        Me.Controls.SetChildIndex(Me.btnInputValue3, 0)
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents lblMessage As MESPdControl.CfLabel
    Friend WithEvents btnInputValue1 As MESPdControl.CfButton
    Friend WithEvents btnInputValue2 As MESPdControl.CfButton
    Friend WithEvents btnInputValue3 As MESPdControl.CfButton
End Class

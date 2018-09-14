<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Public Class frmBase
    Inherits System.Windows.Forms.Form

    'Form は、コンポーネント一覧に後処理を実行するために dispose をオーバーライドします。
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
    'コード エディタでこのプロシージャを変更しないでください。
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(frmBase))
        Me.panelTitle = New System.Windows.Forms.Panel
        Me.btnClose = New System.Windows.Forms.Button
        Me.lblInputModeBack = New System.Windows.Forms.Label
        Me.lblInputMode = New MESPdControl.CfLabel
        Me.lblLoginUserName = New MESPdControl.CfLabel
        Me.btnF5 = New MESPdControl.CfButton
        Me.lblTitle = New MESPdControl.CfLabel
        Me.btnF4 = New MESPdControl.CfButton
        Me.btnF3 = New MESPdControl.CfButton
        Me.btnF2 = New MESPdControl.CfButton
        Me.btnF1 = New MESPdControl.CfButton
        Me.panelTitle.SuspendLayout()
        Me.SuspendLayout()
        '
        'panelTitle
        '
        Me.panelTitle.BackColor = System.Drawing.SystemColors.ActiveCaption
        Me.panelTitle.Controls.Add(Me.btnClose)
        Me.panelTitle.Controls.Add(Me.lblTitle)
        resources.ApplyResources(Me.panelTitle, "panelTitle")
        Me.panelTitle.Name = "panelTitle"
        '
        'btnClose
        '
        Me.btnClose.BackColor = System.Drawing.SystemColors.Menu
        resources.ApplyResources(Me.btnClose, "btnClose")
        Me.btnClose.Name = "btnClose"
        Me.btnClose.TabStop = False
        '
        'lblInputModeBack
        '
        Me.lblInputModeBack.BackColor = System.Drawing.Color.Black
        resources.ApplyResources(Me.lblInputModeBack, "lblInputModeBack")
        Me.lblInputModeBack.Name = "lblInputModeBack"
        '
        'lblInputMode
        '
        resources.ApplyResources(Me.lblInputMode, "lblInputMode")
        Me.lblInputMode.Name = "lblInputMode"
        '
        'lblLoginUserName
        '
        resources.ApplyResources(Me.lblLoginUserName, "lblLoginUserName")
        Me.lblLoginUserName.Name = "lblLoginUserName"
        '
        'btnF5
        '
        resources.ApplyResources(Me.btnF5, "btnF5")
        Me.btnF5.Name = "btnF5"
        Me.btnF5.TabStop = False
        '
        'lblTitle
        '
        Me.lblTitle.BackColor = System.Drawing.SystemColors.ActiveCaption
        resources.ApplyResources(Me.lblTitle, "lblTitle")
        Me.lblTitle.ForeColor = System.Drawing.SystemColors.ActiveCaptionText
        Me.lblTitle.Name = "lblTitle"
        '
        'btnF4
        '
        Me.btnF4.BackColor = System.Drawing.Color.Yellow
        resources.ApplyResources(Me.btnF4, "btnF4")
        Me.btnF4.Name = "btnF4"
        Me.btnF4.TabStop = False
        '
        'btnF3
        '
        Me.btnF3.BackColor = System.Drawing.Color.Green
        Me.btnF3.ForeColor = System.Drawing.Color.White
        resources.ApplyResources(Me.btnF3, "btnF3")
        Me.btnF3.Name = "btnF3"
        Me.btnF3.TabStop = False
        '
        'btnF2
        '
        Me.btnF2.BackColor = System.Drawing.Color.Blue
        Me.btnF2.ForeColor = System.Drawing.Color.White
        resources.ApplyResources(Me.btnF2, "btnF2")
        Me.btnF2.Name = "btnF2"
        Me.btnF2.TabStop = False
        '
        'btnF1
        '
        Me.btnF1.BackColor = System.Drawing.Color.Red
        Me.btnF1.ForeColor = System.Drawing.Color.White
        resources.ApplyResources(Me.btnF1, "btnF1")
        Me.btnF1.Name = "btnF1"
        Me.btnF1.TabStop = False
        '
        'frmBase
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(96.0!, 96.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi
        resources.ApplyResources(Me, "$this")
        Me.Controls.Add(Me.lblInputMode)
        Me.Controls.Add(Me.lblInputModeBack)
        Me.Controls.Add(Me.lblLoginUserName)
        Me.Controls.Add(Me.btnF5)
        Me.Controls.Add(Me.panelTitle)
        Me.Controls.Add(Me.btnF4)
        Me.Controls.Add(Me.btnF3)
        Me.Controls.Add(Me.btnF2)
        Me.Controls.Add(Me.btnF1)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None
        Me.KeyPreview = True
        Me.MaximizeBox = False
        Me.MinimizeBox = False
        Me.Name = "frmBase"
        Me.panelTitle.ResumeLayout(False)
        Me.ResumeLayout(False)

    End Sub
    Protected WithEvents btnF1 As MESPdControl.CfButton
    Protected WithEvents btnF2 As MESPdControl.CfButton
    Protected WithEvents btnF3 As MESPdControl.CfButton
    Protected WithEvents btnF4 As MESPdControl.CfButton
    Friend WithEvents panelTitle As System.Windows.Forms.Panel
    Protected WithEvents lblTitle As MESPdControl.CfLabel
    Protected WithEvents btnClose As System.Windows.Forms.Button
    Protected WithEvents btnF5 As MESPdControl.CfButton
    Friend WithEvents lblLoginUserName As MESPdControl.CfLabel
    Friend WithEvents lblInputMode As MESPdControl.CfLabel
    Friend WithEvents lblInputModeBack As System.Windows.Forms.Label

End Class

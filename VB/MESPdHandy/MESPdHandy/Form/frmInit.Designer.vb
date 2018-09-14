<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Public Class frmInit
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
        Me.timeStartup = New System.Windows.Forms.Timer
        Me.lblLoad = New System.Windows.Forms.Label
        Me.SuspendLayout()
        '
        'timeStartup
        '
        Me.timeStartup.Interval = 1
        '
        'lblLoad
        '
        Me.lblLoad.Font = New System.Drawing.Font("Tahoma", 18.0!, System.Drawing.FontStyle.Regular)
        Me.lblLoad.Location = New System.Drawing.Point(55, 126)
        Me.lblLoad.Name = "lblLoad"
        Me.lblLoad.Size = New System.Drawing.Size(136, 43)
        Me.lblLoad.Text = "Loading..."
        '
        'frmInit
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(96.0!, 96.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi
        Me.AutoScroll = True
        Me.ClientSize = New System.Drawing.Size(240, 320)
        Me.Controls.Add(Me.lblLoad)
        Me.Name = "frmInit"
        Me.Text = "Init"
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents timeStartup As System.Windows.Forms.Timer
    Friend WithEvents lblLoad As System.Windows.Forms.Label

End Class

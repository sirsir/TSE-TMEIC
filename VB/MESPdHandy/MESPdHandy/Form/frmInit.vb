Imports System.IO

Public Class frmInit

    Private mainForm As frmBase
    Private nextForm As frmBase
    Private appData As New AppData

    ''' <summary>
    ''' Form Load event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub frmInit_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        Try
            appData.RootUrl = GetWebUrl()
            timeStartup.Enabled = True

        Catch ex As Exception
            Me.Close()
        End Try

    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub frmClosing(ByVal sender As System.Object, ByVal e As System.EventArgs)

        Try
            nextForm = mainForm.NextForm

            If nextForm Is Nothing Then
                Me.Close()
                Return
            End If

            AddHandler nextForm.Closing, AddressOf frmClosing
            AddHandler nextForm.Closed, AddressOf frmClosed

            nextForm.AppData = Me.appData
            nextForm.Show()
        Catch ex As Exception

        End Try
    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub frmClosed(ByVal sender As System.Object, ByVal e As System.EventArgs)

        Try
            Me.mainForm.Dispose()
            Me.mainForm = Me.nextForm
        Catch ex As Exception

        End Try
    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="form"></param>
    ''' <remarks></remarks>
    Private Sub SetNextForm(ByVal form As frmBase)

        Me.mainForm = form
        AddHandler Me.mainForm.Closing, AddressOf Me.frmClosing
        AddHandler Me.mainForm.Closed, AddressOf Me.frmClosed
        Me.mainForm.TopMost = True
        Me.mainForm.Show()

    End Sub

    Private Sub timeStartup_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles timeStartup.Tick

        Try
            timeStartup.Enabled = False

            mainForm = New frmLogin()
            AddHandler mainForm.Closing, AddressOf frmClosing
            AddHandler mainForm.Closed, AddressOf frmClosed
            mainForm.AppData = Me.appData
            mainForm.Owner = Me
            mainForm.Show()

        Catch ex As Exception
            Me.Close()
        End Try
    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Private Function GetWebUrl() As String

        Dim stream As StreamReader = Nothing
        Dim webUrl As String = String.Empty

        Try
            stream = New StreamReader(CfUtils.GetCurrentDirectory() & "\Config\WebUrl.txt")
            webUrl = stream.ReadToEnd()

        Catch ex As Exception
        Finally
            If stream IsNot Nothing Then stream.Close()
        End Try

        Return webUrl.Trim()

    End Function

End Class

Imports System.Net
Imports MESPdHandy.Service
Imports Calib.SystemLibNet.Def

''' <summary>
''' Login Form
''' </summary>
''' <remarks></remarks>
Public Class frmLogin
    Inherits frmBase

    Private isPasswordRequired As Boolean

    ''' <summary>
    ''' Form Load event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub frmLogin_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        Try
            Me.txtUserId.Focus()

            Me.scan = ScanCodeFactory.ScanCode()
            Me.scan.Connect()

            Me.isPasswordRequired = UserService.IsPasswordRequired()

            If Not isPasswordRequired Then
                Me.lblPassword.Hide()
                Me.txtPassword.Hide()
            End If

        Catch ex As CodeScanException
            MsgBoxUtils.Err("SCAN ERROR")
        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' Form Closing event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub frmLogin_Closing(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Closing

        Try
            Me.scan.Disconnect()
        Catch ex As CodeScanException
            MsgBoxUtils.Err("SCAN ERROR")
        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' Login button click event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Protected Overrides Sub btnF4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

        Me.Login()
    End Sub


    Protected Sub Login()

        Try
            If String.IsNullOrEmpty(Me.txtUserId.Text) Then
                Throw New MesPdException(My.Resources.ErrorNotInputUserId)
            End If

            If isPasswordRequired Then
                If String.IsNullOrEmpty(Me.txtPassword.Text) Then
                    Throw New MesPdException(My.Resources.ErrorNotInputPassword)
                End If
            End If

            If Not UserService.IsAuth(Me.txtUserId.Text, Me.txtPassword.Text) Then
                Throw New MesPdException(My.Resources.ErrorLogin)
            End If

            Me.AppData.User = UserService.GetUserInfo(Me.txtUserId.Text, Me.txtPassword.Text)

            'MsgBoxUtils.Err("dddddd")


            Me.MoveNextForm(New frmProcessRead())

        Catch ex As MesPdException
            MsgBoxUtils.Warning(ex.Message)
        Catch ex As WebException
            MsgBoxUtils.Err("NETWORK ERROR")
        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub


    ''' <summary>
    ''' Text input gotfocus event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub txt_GotFocus(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles txtUserId.GotFocus, txtPassword.GotFocus

        Try
            Me.InputModeAlphaNumeric()
            Me.txtUserId.SelectAll()

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' Get the scan data
    ''' </summary>
    ''' <param name="data"></param>
    ''' <remarks></remarks>
    Protected Friend Overrides Sub GetScanCode(ByVal data As String)

        Try
            If String.IsNullOrEmpty(data) Then Return

            Me.txtUserId.Text = data

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    Private Sub txtPassword_KeyDown(ByVal sender As System.Object, ByVal e As System.Windows.Forms.KeyEventArgs) Handles txtPassword.KeyDown

        Try
            Select Case e.KeyCode


                Case Keys.Enter
                    Me.Login()



            End Select

        Catch ex As Exception
            'MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

End Class
Imports Calib.SystemLibNet.Def

''' <summary>
''' Base form
''' </summary>
''' <remarks></remarks>
Public Class frmBase

    ''' <summary>NextForm</summary>
    Public NextForm As frmBase

    ''' <summary>AppData</summary>
    Public AppData As AppData

    ''' <summary>Scan Interface</summary>
    Public scan As ScanCodeImpl

    ''' <summary>
    ''' Form load event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub frmBase_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        Try
            If String.IsNullOrEmpty(Me.AppData.User.UserId) Then
                Me.lblLoginUserName.Text = String.Empty
                Me.btnF5.Enabled = False
            Else
                Me.lblLoginUserName.Text = Me.AppData.User.UserName
                Me.btnF5.Enabled = True
            End If

            'Key allocation change
            'F1:(Alt)→F1、F2:(Shift+Tab)→F2、F3:(Tab)→F3、F4:(F4)→F4
            DtxUtils.SetUserDefineKey(KEYID_F1, Keys.F1)
            DtxUtils.SetUserDefineKey(KEYID_F2, Keys.F2)
            DtxUtils.SetUserDefineKey(KEYID_F3, Keys.F3)
            DtxUtils.SetUserDefineKey(KEYID_F4, Keys.F4)
            DtxUtils.SetUserDefineKey(KEYID_F5, Keys.F5)
            DtxUtils.SetUserDefineKey(KEYID_F6, Keys.F6)
            DtxUtils.SetUserDefineKey(KEYID_F8, Keys.F23)

            Me.lblInputMode.Text = DtxUtils.GetInputModeName()

        Catch ex As Exception

        End Try
    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub btnClose_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnClose.Click
        Me.Close()
    End Sub

    ''' <summary>
    ''' F1 button click event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Protected Overridable Sub btnF1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnF1.Click
        ' This event is not deleted
        ' Required by inheritance
    End Sub

    ''' <summary>
    ''' F2 button click event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Protected Overridable Sub btnF2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnF2.Click
        ' This event is not deleted
        ' Required by inheritance
    End Sub

    ''' <summary>
    ''' F3 button click event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Protected Overridable Sub btnF3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnF3.Click
        ' This event is not deleted
        ' Required by inheritance
    End Sub

    ''' <summary>
    ''' F4 button click event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Protected Overridable Sub btnF4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnF4.Click
        ' This event is not deleted
        ' Required by inheritance
    End Sub

    ''' <summary>
    ''' Logout button click event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub btnF5_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnF5.Click

        Try
            Dim isResult As MsgBoxResult = MsgBoxUtils.YesNo(My.Resources.LogOutConfirm)

            If isResult = MsgBoxResult.No Then Return

            Me.AppData.User = New UserInfo()

            Me.MoveNextForm(New frmLogin())

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub


    Protected Overridable Sub btnF6_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnF4.Click
        ' This event is not deleted
        ' Required by inheritance
    End Sub

    ''' <summary>
    ''' Key down event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Protected Overridable Sub frmBase_KeyDown(ByVal sender As System.Object, ByVal e As System.Windows.Forms.KeyEventArgs) Handles MyBase.KeyDown

        Try
            Select Case e.KeyCode
                Case Keys.F1
                    btnF1_Click(Me, Nothing)
                Case Keys.F2
                    btnF2_Click(Me, Nothing)
                Case Keys.F3
                    btnF3_Click(Me, Nothing)
                Case Keys.F4
                    btnF4_Click(Me, Nothing)
                Case Keys.F5
                    btnF5_Click(Me, Nothing)
                Case Keys.F20, Keys.F21, Keys.F24, Keys.None
                    ScanCode()
                Case Keys.F23
                    Me.lblInputMode.Text = DtxUtils.GetInputModeName()
                Case Keys.Up
                    Me.SelectNextControl(CfUtils.ActiveControl(Me), False, True, True, True)
                Case Keys.Down
                    Me.SelectNextControl(CfUtils.ActiveControl(Me), True, True, True, True)
                Case Keys.Enter

                    Dim control As Control = CfUtils.ActiveControl(Me)

                    If TypeOf control Is Button Then Return

                    Me.SelectNextControl(control, True, True, True, True)
            End Select

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub frmBase_KeyUp(ByVal sender As System.Object, ByVal e As System.Windows.Forms.KeyEventArgs) Handles MyBase.KeyUp

        Try
            Select Case e.KeyCode
                Case Keys.F20, Keys.F21, Keys.F24
                    ScanCodeStop()
            End Select

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' Close the form in the display to display the following form
    ''' </summary>
    ''' <param name="nextFrom"></param>
    ''' <remarks></remarks>
    Protected Friend Sub MoveNextForm(ByVal nextFrom As frmBase)
        Me.NextForm = nextFrom
        Me.Close()
    End Sub

    Protected Friend Sub ScanCode()

        Try
            If scan Is Nothing Then Return

            scan.ScanStart()
        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    Protected Friend Sub ScanCodeStop()

        Try


            If scan Is Nothing Then Return


            scan.ScanStop()

            Dim readData As String = scan.GetScanData()
            GetScanCode(readData.Trim())

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    Protected Friend Overridable Sub GetScanCode(ByVal data As String)

    End Sub

    Protected Friend Sub InputModeNumeric()

        DtxUtils.InputModeNumeric()
        Me.lblInputMode.Text = DtxUtils.GetInputModeName()

    End Sub

    Protected Friend Sub InputModeAlphaNumeric()

        DtxUtils.InputModeAlphaNumeric()
        Me.lblInputMode.Text = DtxUtils.GetInputModeName()

    End Sub

    Protected Friend Sub InputModeString()

        DtxUtils.InputModeString()
        Me.lblInputMode.Text = DtxUtils.GetInputModeName()

    End Sub

End Class

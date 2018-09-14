Imports System.Net
Imports MESPdHandy.Service

''' <summary>
''' Process Read Form
''' </summary>
''' <remarks></remarks>
Public Class frmProcessRead
    Inherits frmBase

    ''' <summary>
    ''' Form Load event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub frmProcessRead_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load


        'Me.scan = ScanCodeFactory.ScanCode()
        'Me.scan.Connect()

        'Me.ListBox1.Items.Add("dddd")
        'Me.ListBox1.Items.Add("sss")

        'Dim json As String = ProcessService.GetAllProcessHandy()
        'Dim json As New List(Of ProcessInfo)= ProcessService.GetAllProcessHandy()

        Try
            Dim processes As List(Of ProcessInfo) = ProcessService.GetAllProcessHandy()
            For Each p In processes
                'MsgBox(json)
                'Debug.Write(json)
                'Me.ListBox1.Items.Add(json)
                Me.ListBox1.Items.Add(p.ProcessName)
            Next p
            Me.ListBox1.SelectedIndex = 0
        Catch ex As WebException
            MsgBoxUtils.Err("NETWORK ERROR")
        End Try

        


    End Sub




    Protected Overrides Sub frmBase_KeyDown(ByVal sender As System.Object, ByVal e As System.Windows.Forms.KeyEventArgs)

        Try
            Select Case e.KeyCode

                Case Keys.Up
                    'Me.SelectNextControl(CfUtils.ActiveControl(Me), False, True, True, True)
                    'Me.txtSerialNumber.SelectAll()
                Case Keys.Down
                    'Me.SelectNextControl(CfUtils.ActiveControl(Me), True, True, True, True)
                    'Me.txtSerialNumber.SelectAll()

            End Select

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
    Private Sub frmProcessRead_Closing(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Closing

        'Try
        'Me.scan.Disconnect()
        'Catch ex As CodeScanException
        'MsgBoxUtils.Err("SCAN ERROR")
        'Catch ex As Exception
        'MsgBoxUtils.Err("ERROR")
        'End Try
    End Sub

    Private Sub ListBox1_KeyDown(ByVal sender As System.Object, ByVal e As System.Windows.Forms.KeyEventArgs) Handles ListBox1.KeyDown

        Try
            Select Case e.KeyCode


                Case Keys.Enter


                    Me.ReadProcessEnter()


            End Select

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' Process code defined click event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub btnEnter_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnEnter.Click

        Me.ReadProcessEnter()

    End Sub

    ''' <summary>
    ''' Text input gotfocus event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub txt_GotFocus(ByVal sender As System.Object, ByVal e As System.EventArgs)

        Try
            Me.InputModeAlphaNumeric()
            'Me.txtProcessCode.SelectAll()

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

            'Me.txtProcessCode.Text = StringUtils.TruncateLength(data, Me.txtProcessCode.MaxLength)

            Me.ReadProcessEnter()

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' It will move to the result input process
    ''' </summary>
    ''' <remarks></remarks>
    Private Sub ReadProcessEnter()

        Try
            'Dim processInfo As ProcessInfo = ProcessService.GetProcessInfoByBarcode(Me.txtProcessCode.Text)

            'Debug.Write(Me.ListBox1.SelectedItem.ToString())
            Dim processInfo As ProcessInfo = ProcessService.GetProcessInfoByName(Me.ListBox1.SelectedItem.ToString())
            'Debug.Write(Me.ListBox1.SelectedItem.ToString())
            'Debug.Write(processInfo.ProcessName)

            If processInfo Is Nothing Then
                Throw New MesPdException(My.Resources.NoneData)
            End If
            'Debug.Write(Me.ListBox1.SelectedItem.ToString())

            Me.AppData.Process = processInfo

            Me.MoveNextForm(New frmInputWork())

        Catch ex As MesPdException
            MsgBoxUtils.Warning(ex.Message)
        Catch ex As WebException
            MsgBoxUtils.Err("NETWORK ERROR")
        End Try
    End Sub

    Private Sub btnF5_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnF5.Click
        Me.MoveNextForm(New frmLogin())
    End Sub
End Class
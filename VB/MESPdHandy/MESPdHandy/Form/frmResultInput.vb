Imports System.Net
Imports MESPdHandy.Service

''' <summary>
''' Result Input Form
''' </summary>
''' <remarks></remarks>
Public Class frmResultInput
    Inherits frmBase

    ''' <summary>
    ''' Form Load event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub frmResultInput_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        Try
            Me.RefreshProcessInfo()
        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' F1 button click event
    ''' Move to Process Read Form
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Protected Overrides Sub btnF1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

        Try
            Me.MoveNextForm(New frmProcessRead())
        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' Start button click event
    ''' To start the process
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub btnStart_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnStart.Click

        Try
            Dim process As ProcessInfo = Me.AppData.Process

            If process.Status = ProcessStatus.START Then
                Throw New MesPdException(My.Resources.ErrorProcessStarted)
            End If

            Dim isStart As Boolean = ProcessService.ProcessWorkStart(process.ProductPlanNo, process.ProcessComponentNo)

            If Not isStart Then
                MsgBoxUtils.Warning(My.Resources.ErrorCanNotProcessStart)
            End If

            Me.RefreshProcessInfo()

        Catch ex As MesPdException
            MsgBoxUtils.Warning(ex.Message)
        Catch ex As WebException
            MsgBoxUtils.Err("NETWORK ERROR")
        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' Finish button click event
    ''' finish the process
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub btnFinish_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnFinish.Click

        Try
            Dim process As ProcessInfo = Me.AppData.Process

            Select Case process.Status
                Case ProcessStatus.NONE
                    MsgBoxUtils.Warning(My.Resources.ErrorNotStart)
                    Return
                Case ProcessStatus.COMPLETE
                    MsgBoxUtils.Warning(My.Resources.ErrorFinished)
                    Return
            End Select

            Dim specProcesses As List(Of SpecInfo) = ProcessService.GetSpecInfo(process.ProductPlanNo, process.ProcessComponentNo)

            If specProcesses IsNot Nothing Then
                If specProcesses.Count > 0 Then

                    Me.AppData.SpecProcesses = specProcesses
                    Me.MoveSpecProduct()
                    Return
                End If
            End If

            Dim isFinish As Boolean = ProcessService.ProcessWorkFinish(process.ProductPlanNo, process.ProcessComponentNo)

            If Not isFinish Then
                MsgBoxUtils.Warning(My.Resources.ErrorCanNotFinish)
            End If

            Me.RefreshProcessInfo()

        Catch ex As WebException
            MsgBoxUtils.Err("NETWORK ERROR")
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
    Private Sub btnProduct_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnProduct.Click

        Try
            Dim process As ProcessInfo = Me.AppData.Process

            Select Case process.Status
                Case ProcessStatus.NONE
                    MsgBoxUtils.Warning(My.Resources.ErrorNotStart)
                    Return
                Case ProcessStatus.COMPLETE
                    MsgBoxUtils.Warning(My.Resources.ErrorFinished)
                    Return
            End Select

            ' Initialize
            Me.AppData.SerialNoInfo = New SerialNoInfo()

            Me.MoveNextForm(New frmInputWork())

        Catch ex As WebException
            MsgBoxUtils.Err("NETWORK ERROR")
        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <remarks></remarks>
    Private Sub RefreshProcessInfo()

        Dim process As ProcessInfo = ProcessService.GetProcessInfo(Me.AppData.Process.ProductPlanNo, Me.AppData.Process.ProcessComponentNo)

        If process Is Nothing Then
            MsgBoxUtils.Warning(My.Resources.NoneData())
            Return
        End If

        Me.AppData.Process = process

        Me.lblProcessName.Text = process.ProcessName
        Me.lblProductPlanNo.Text = process.ProductPlanNo
        Me.lblProductName.Text = process.ProductName
        Me.lblStateName.Text = ProcessStatus.GetName(process.Status)

        'For TextAlign does not work, adjust the blank
        Me.lblCount.Text = String.Format("{0}/{1}", process.ResultQty, process.PlanQty).PadLeft(13)

    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <remarks></remarks>
    Private Sub MoveSpecProduct()

        If Me.AppData.SpecProcesses.Count = 0 Then Return

        Me.AppData.NowSpecType = MESPdHandy.AppData.SPEC_TYPE.PROCESS

        Dim spec As SpecInfo = DisplayOrderUtils.FirstOrder(Me.AppData.SpecProcesses)
        Me.AppData.NowDisplayOrder = spec.DisplayOrder

        Me.MoveNextForm(SpecFormUtils.GetSpecForm(spec.SpecAttributeId))
    End Sub

End Class
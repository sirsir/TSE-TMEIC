Imports System.Net
Imports MESPdHandy.Service

''' <summary>
''' 
''' </summary>
''' <remarks></remarks>
Public Class frmSpec
    Inherits frmSerial

    ''' <summary>
    ''' Form Load event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub frmSpec_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        Try
            If AppDomain.CurrentDomain.FriendlyName = "DefaultDomain" Then Return

            Dim spec As SpecInfo = DisplayOrderUtils.SelectOrder(Me.AppData.SpecInfo(), Me.AppData.NowDisplayOrder)

            Me.lblSpecName.Text = spec.SpecName

        Catch ex As Exception
            'MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' F1 button click event
    ''' Move spec_type is to process if the ResultInputFrom
    ''' Move spec_type is to product if the ResultInputProductFrom
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Protected Overrides Sub btnF1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

        Try
            Select Case Me.AppData.NowSpecType
                Case MESPdHandy.AppData.SPEC_TYPE.PROCESS
                    Me.MoveNextForm(New frmResultInput())

                Case MESPdHandy.AppData.SPEC_TYPE.PRODUCT
                    Me.MoveNextForm(New frmInputWork())
            End Select

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' F2 button click event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Protected Overrides Sub btnF2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

        Try
            Dim spec As SpecInfo = DisplayOrderUtils.PrevOrder(Me.AppData.SpecInfo(), Me.AppData.NowDisplayOrder)
            Me.AppData.NowDisplayOrder = spec.DisplayOrder

            Me.MoveNextForm(SpecFormUtils.GetSpecForm(spec.SpecAttributeId))

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' F3 button click event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Protected Overrides Sub btnF3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

        Try
            Dim spec As SpecInfo = DisplayOrderUtils.NextOrder(Me.AppData.SpecInfo(), Me.AppData.NowDisplayOrder)
            Me.AppData.NowDisplayOrder = spec.DisplayOrder

            Me.MoveNextForm(SpecFormUtils.GetSpecForm(spec.SpecAttributeId))

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' F4 button click event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Protected Overrides Sub btnF4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

        Try
            Dim process As ProcessInfo = Me.AppData.Process

            Select Case Me.AppData.NowSpecType
                Case MESPdHandy.AppData.SPEC_TYPE.PROCESS
                    ProcessService.SpecRegister(process.ProductPlanNo, process.ProcessComponentNo, Me.AppData.SpecInfo())

                    Dim isFinish As Boolean = ProcessService.ProcessWorkFinish(process.ProductPlanNo, process.ProcessComponentNo)

                    If Not isFinish Then
                        Throw New MesPdException(My.Resources.ErrorCanNotFinish)
                    End If

                    'Me.MoveNextForm(New frmResultInput())
                    Me.MoveNextForm(New frmInputWork())

                Case MESPdHandy.AppData.SPEC_TYPE.PRODUCT
                    Dim serialNo As String = Me.AppData.SerialNoInfo.SerialNo
                    ProductService.SpecRegister(process.ProductPlanNo, process.ProcessComponentNo, serialNo, Me.AppData.SpecInfo())

                    'MsgBoxUtils.Err("will add")

                    WorkFinish2(Me.AppData.Process, Me.AppData.User)

                    Me.AppData.Process.ProductName = ""

                    Me.MoveNextForm(New frmInputWork())
            End Select


        Catch ex As MesPdException
            MsgBoxUtils.Err(ex.Message)
        Catch ex As WebException
            MsgBoxUtils.Err("NETWORK ERROR")
        Catch ex As Exception
            'MsgBoxUtils.Err("ERROR")
        End Try
    End Sub


    '== Modified from frmInputWork
    Private Sub WorkFinish2(ByVal process As ProcessInfo, ByVal user As UserInfo)

        If process.Status <> ProcessProductStatus.COMPLETE Then

            Dim isComplete As Boolean = ProductService.WorkEnd(process.ProductPlanNo, process.ProcessComponentNo, process.SerialNo, user)

            If isComplete Then
                AppData.Process.Status = ProcessProductStatus.COMPLETE
            Else
                Throw New MesPdException(My.Resources.ErrorCanNotFinish)
            End If
        End If
    End Sub

End Class
Imports System.Net
Imports MESPdHandy.Service

Public Class frmResultInputProduct
    Inherits frmBase

    ''' <summary>
    ''' Form Load event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub frmResultInputProduct_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        Try
            scan = ScanCodeFactory.ScanCode()
            scan.Connect()

            Me.RefreshProductInfo()
            Me.txtSerialNumber.Focus()

        Catch ex As CodeScanException
            MsgBoxUtils.Err("SCAN ERROR")
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
    Private Sub frmResultInputProduct_Closing(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Closing

        Try
            scan.Disconnect()
        Catch ex As CodeScanException
            MsgBoxUtils.Err("SCAN ERROR")
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
    Protected Overrides Sub btnF1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

        Try
            Me.MoveNextForm(New frmResultInput())
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
    Protected Overrides Sub btnF2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

        Try
            Me.MoveMaterialRead()

        Catch ex As MesPdException
            MsgBoxUtils.Err(ex.Message)
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
    Protected Overrides Sub btnF3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

        Try
            Me.MoveSpecProduct()

        Catch ex As MesPdException
            MsgBoxUtils.Err(ex.Message)
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
    Private Sub txtSerialNumber_KeyDown(ByVal sender As System.Object, ByVal e As System.Windows.Forms.KeyEventArgs) Handles txtSerialNumber.KeyDown

        Try
            Select Case e.KeyCode
                Case Keys.Up, Keys.Down
                    Me.txtSerialNumber.Text = Me.AppData.SerialNoInfo.SerialNo
                    Me.lblStateName.Text = ProcessProductStatus.GetName(Me.AppData.SerialNoInfo.Status)

                Case Keys.Enter
                    Dim process As ProcessInfo = Me.AppData.Process
                    Me.AppData.SerialNoInfo = Me.GetSerialNoInfo(process.ProductPlanNo, process.ProcessComponentNo, Me.txtSerialNumber.Text)
                    Me.RefreshProductInfo()
            End Select

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' Get SerialNo info
    ''' </summary>
    ''' <param name="productPlanNo"></param>
    ''' <param name="processComponentNo"></param>
    ''' <param name="serialNo"></param>
    ''' <remarks></remarks>
    Private Function GetSerialNoInfo( _
        ByVal productPlanNo As String, ByVal processComponentNo As Integer, ByVal serialNo As String) As SerialNoInfo

        Dim serialNoInfo As SerialNoInfo = _
            ProductService.GetSerialNoInfo(productPlanNo, processComponentNo, serialNo)

        If serialNoInfo Is Nothing Then

            serialNoInfo = New SerialNoInfo()
            serialNoInfo.SerialNo = serialNo
            serialNoInfo.Materials = New List(Of MaterialInfo)
            serialNoInfo.SpecProducts = New List(Of SpecInfo)
        End If

        Return serialNoInfo

    End Function

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub btnStart_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnStart.Click

        Try
            Dim process As ProcessInfo = Me.AppData.Process
            Dim serialNoInfo As New SerialNoInfo()

            If String.IsNullOrEmpty(Me.txtSerialNumber.Text) Then
                serialNoInfo.SerialNo = Me.CreateSerialNo(process.ProductPlanNo, process.ProcessComponentNo, Me.txtSerialNumber.Text)
            Else
                serialNoInfo = Me.GetSerialNoInfo(process.ProductPlanNo, process.ProcessComponentNo, Me.txtSerialNumber.Text)
            End If

            Dim isExists As Boolean = _
                ProductService.ExistsPreviousResult(process.ProductPlanNo, process.ProcessComponentNo, serialNoInfo.SerialNo)

            If Not isExists Then
                If MsgBoxUtils.YesNo(My.Resources.ErrorNoResultsPreviousProcess) = MsgBoxResult.No Then
                    Return
                End If
            End If

            Me.WorkStart(process, serialNoInfo)
            Me.AppData.SerialNoInfo = Me.GetSerialNoInfo(process.ProductPlanNo, process.ProcessComponentNo, serialNoInfo.SerialNo)

            If Not Me.AppData.IsMaterialInputCompletion() Then
                Me.MoveMaterialRead()
                Return
            End If

            If Not Me.AppData.IsSpecProductInputCompletion() Then
                Me.MoveSpecProduct()
                Return
            End If

            Me.RefreshProductInfo()

        Catch ex As MesPdException
            MsgBoxUtils.Err(ex.Message)
        Catch ex As WebException
            MsgBoxUtils.Err("NETWORK ERROR")
        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' Create SerialNo
    ''' </summary>
    ''' <param name="productPlanNo"></param>
    ''' <param name="processComponentNo"></param>
    ''' <param name="serialNo"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Private Function CreateSerialNo( _
        ByVal productPlanNo As String, ByVal processComponentNo As Integer, ByVal serialNo As String) As String

        Dim newSerialNo As String = ProductService.CreateSerialNo(productPlanNo, processComponentNo, serialNo)

        If newSerialNo Is Nothing Then
            Throw New MesPdException(My.Resources.ErrorSerialNoCreation)
        End If

        Return newSerialNo

    End Function

    ''' <summary>
    ''' Start working on the product
    ''' </summary>
    ''' <param name="process"></param>
    ''' <param name="serial"></param>
    ''' <remarks></remarks>
    Private Sub WorkStart(ByVal process As ProcessInfo, ByVal serial As SerialNoInfo)

        If serial.Status <> ProcessProductStatus.START Then

            Dim isStart As Boolean = ProductService.WorkStart(process.ProductPlanNo, process.ProcessComponentNo, serial.SerialNo)

            If Not isStart Then
                Throw New MesPdException(My.Resources.ErrorCanNotStart)
            End If
        End If
    End Sub

    ''' <summary>
    ''' Finish the work of the product
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub btnFinish_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnFinish.Click

        Try
            Dim serialNoInfo As SerialNoInfo = Me.AppData.SerialNoInfo
            Dim process As ProcessInfo = Me.AppData.Process

            If String.IsNullOrEmpty(serialNoInfo.SerialNo) Then
                Return
            End If

            Me.WorkFinish(process, serialNoInfo, Me.AppData.User)

            ' Clear to read the following serial
            Me.AppData.SerialNoInfo = New SerialNoInfo()

            Me.RefreshProductInfo()

        Catch ex As MesPdException
            MsgBoxUtils.Err(ex.Message)
        Catch ex As WebException
            MsgBoxUtils.Err("NETWORK ERROR")
        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="process"></param>
    ''' <param name="serialNoInfo"></param>
    ''' <param name="user"></param>
    ''' <remarks></remarks>
    Private Sub WorkFinish(ByVal process As ProcessInfo, ByVal serialNoInfo As SerialNoInfo, ByVal user As UserInfo)

        If serialNoInfo.Status <> ProcessProductStatus.COMPLETE Then

            Dim isComplete As Boolean = ProductService.WorkEnd(process.ProductPlanNo, process.ProcessComponentNo, serialNoInfo.SerialNo, user)

            If Not isComplete Then
                Throw New MesPdException(My.Resources.ErrorCanNotFinish)
            End If
        End If
    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub txt_GotFocus(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles txtSerialNumber.GotFocus

        Try
            Me.InputModeAlphaNumeric()
            Me.txtSerialNumber.SelectAll()

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="data"></param>
    ''' <remarks></remarks>
    Protected Friend Overrides Sub GetScanCode(ByVal data As String)

        Try
            If String.IsNullOrEmpty(data) Then Return

            Me.txtSerialNumber.Text = StringUtils.TruncateLength(data, Me.txtSerialNumber.MaxLength)

            Dim process As ProcessInfo = Me.AppData.Process

            Me.AppData.SerialNoInfo = Me.GetSerialNoInfo(process.ProductPlanNo, process.ProcessComponentNo, Me.txtSerialNumber.Text)
            Me.RefreshProductInfo()

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <remarks></remarks>
    Private Sub RefreshProductInfo()

        Dim process As ProcessInfo = ProcessService.GetProcessInfo(Me.AppData.Process.ProductPlanNo, Me.AppData.Process.ProcessComponentNo)

        If process Is Nothing Then
            'It continues in the previous value
            process = Me.AppData.Process
        Else
            Me.AppData.Process = process
        End If

        Me.lblProcessName.Text = process.ProcessName
        Me.lblProductPlanNo.Text = process.ProductPlanNo
        Me.lblProductName.Text = process.ProductName

        Me.txtSerialNumber.Text = Me.AppData.SerialNoInfo.SerialNo
        Me.lblStateName.Text = ProcessProductStatus.GetName(Me.AppData.SerialNoInfo.Status)

        'For TextAlign does not work, adjust the blank
        Me.lblCount.Text = String.Format("{0}/{1}", process.ResultQty, process.PlanQty).PadLeft(13)

    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <remarks></remarks>
    Private Sub MoveMaterialRead()

        If Me.AppData.SerialNoInfo.Materials.Count = 0 Then Return

        Dim material As MaterialInfo = DisplayOrderUtils.FirstOrder(Me.AppData.SerialNoInfo.Materials)
        Me.AppData.NowDisplayOrder = material.DisplayOrder

        Me.MoveNextForm(New frmMaterialRead())
    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <remarks></remarks>
    Private Sub MoveSpecProduct()

        If Me.AppData.SerialNoInfo.SpecProducts.Count = 0 Then Return

        Me.AppData.NowSpecType = MESPdHandy.AppData.SPEC_TYPE.PRODUCT

        Dim spec As SpecInfo = DisplayOrderUtils.FirstOrder(Me.AppData.SerialNoInfo.SpecProducts)
        Me.AppData.NowDisplayOrder = spec.DisplayOrder

        Me.MoveNextForm(SpecFormUtils.GetSpecForm(spec.SpecAttributeId))
    End Sub

End Class
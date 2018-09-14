Imports System.Net
Imports MESPdHandy.Service

Public Class frmInputWork
    Inherits frmBase

    ''' <summary>
    ''' Form Load event
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    ''' 
    Private Sub frmInputWork_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load


      btnF2.Enabled = False

        'Dim process As ProcessInfo = ProcessService.GetProcessInfo(Me.AppData.Process.ProductPlanNo, Me.AppData.Process.ProcessComponentNo)
        Dim process As ProcessInfo = Me.AppData.Process

        'If ProcessInfo Is Nothing Then
        If Trim(process.ProductName & vbNullString) = vbNullString Then
            'Me.AppData.Process = processInfo

            lblProcessName.Text = process.ProcessName
            'lblProcessName.Text = Me.AppData.Process.ProcessName & Me.AppData.Process.ProcessComponentNo

            txtSerialNumber.Text = AppData.ForTest(0)

            lblProductName.Text = ""
            lblProductPlanNo.Text = ""
            lblStateName.Text = ""
            Me.Update()



            'lblProductName.Text = process.ProcessName
            'lblProductPlanNo.Text = process.ProductPlanNo
            'lblStateName.Text = process.Status
        Else
            lblProcessName.Text = process.ProcessName
            'lblProcessName.Text = Me.AppData.Process.ProcessName & Me.AppData.Process.ProcessComponentNo

            txtSerialNumber.Text = Me.AppData.Process.barcode


            lblProductName.Text = process.ProductName
            lblProductPlanNo.Text = process.ProductPlanNo
            'lblStateName.Text = process.Status
            'Me.lblStateName.Text = ProcessProductStatus.GetName(Me.AppData.SerialNoInfo.Status)
            Me.lblStateName.Text = ProcessProductStatus.GetName(process.Status)
        End If




    End Sub



    Protected Overrides Sub frmBase_KeyDown(ByVal sender As System.Object, ByVal e As System.Windows.Forms.KeyEventArgs)

        Try
            Select Case e.KeyCode

                Case Keys.Up
                    'Me.SelectNextControl(CfUtils.ActiveControl(Me), False, True, True, True)
                    Me.txtSerialNumber.SelectAll()
                Case Keys.Down
                    'Me.SelectNextControl(CfUtils.ActiveControl(Me), True, True, True, True)
                    Me.txtSerialNumber.SelectAll()
                Case Keys.Clear
                    'Dim process As ProcessInfo = Me.AppData.Process
                    'Me.AppData.SerialNoInfo = Me.GetSerialNoInfo(process.ProductPlanNo, process.ProcessComponentNo, Me.txtSerialNumber.Text)
                    'Me.RefreshProductInfo()
                    lblProductName.Text = ""
                    lblProductPlanNo.Text = ""
                    txtSerialNumber.Text = ""
                    lblStateName.Text = ""
                    btnStart.Enabled = False
                    Me.Update()
                    'Case Keys.F20, Keys.F21, Keys.F24, Keys.None
                    'txtSerialNumber.SelectAll()

                    ' ScanCode()

            End Select

        Catch ex As Exception
            MsgBoxUtils.Err("ERROR")
        End Try
    End Sub




    Private Sub frmInputWork_LoadOLD(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load




        'Dim process As ProcessInfo = ProcessService.GetProcessInfo(Me.AppData.Process.ProductPlanNo, Me.AppData.Process.ProcessComponentNo)
        Dim process As ProcessInfo = Me.AppData.Process

        'If ProcessInfo Is Nothing Then
        If Trim(Process.ProductName & vbNullString) = vbNullString Then
            'Me.AppData.Process = processInfo

            lblProcessName.Text = Process.ProcessName
            'lblProcessName.Text = Me.AppData.Process.ProcessName & Me.AppData.Process.ProcessComponentNo

            txtSerialNumber.Text = AppData.ForTest(0)

            lblProductName.Text = ""
            lblProductPlanNo.Text = ""
            lblStateName.Text = ""
            Me.Update()


            'lblProductName.Text = process.ProcessName
            'lblProductPlanNo.Text = process.ProductPlanNo
            'lblStateName.Text = process.Status
        Else
            lblProcessName.Text = Process.ProcessName
            'lblProcessName.Text = Me.AppData.Process.ProcessName & Me.AppData.Process.ProcessComponentNo

            txtSerialNumber.Text = Me.AppData.Process.barcode


            lblProductName.Text = Process.ProductName
            lblProductPlanNo.Text = Process.ProductPlanNo
            'lblStateName.Text = process.Status
            'Me.lblStateName.Text = ProcessProductStatus.GetName(Me.AppData.SerialNoInfo.Status)
            Me.lblStateName.Text = ProcessProductStatus.GetName(Process.Status)
        End If



    End Sub




    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="sender"></param>
    ''' <param name="e"></param>
    ''' <remarks></remarks>
    Private Sub frmInputWork_Closing(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Closing

        Try
            scan.Disconnect()
        Catch ex As CodeScanException
            MsgBoxUtils.Err("SCAN ERROR")
        Catch ex As Exception
            'MsgBoxUtils.Err("ERROR")
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
                'Case Keys.Up, Keys.Down
                'Me.txtSerialNumber.SelectAll()
                    'Me.txtSerialNumber.Text = Me.AppData.SerialNoInfo.SerialNo
                    'Me.lblStateName.Text = ProcessProductStatus.GetName(Me.AppData.SerialNoInfo.Status)

                Case Keys.Clear
                    'Dim process As ProcessInfo = Me.AppData.Process
                    'Me.AppData.SerialNoInfo = Me.GetSerialNoInfo(process.ProductPlanNo, process.ProcessComponentNo, Me.txtSerialNumber.Text)
                    'Me.RefreshProductInfo()
                    lblProductName.Text = ""
                    lblProductPlanNo.Text = ""
                    txtSerialNumber.Text = ""
                    lblStateName.Text = ""
                    btnStart.Enabled = False
                    Me.Update()


                Case Keys.Enter
                    

                    Me.UpdateBarcode()


                Case Keys.F20, Keys.F21, Keys.F24, Keys.None
                    lblProductName.Text = ""
                    lblProductPlanNo.Text = ""
                    txtSerialNumber.SelectAll()
                    lblStateName.Text = ""
                    btnStart.Enabled = False








            End Select

        Catch ex As Exception
            'MsgBoxUtils.Err("ERROR")
        End Try
    End Sub




    Sub UpdateBarcode()

        lblProductName.Text = "-- Searching ---"
        lblProductPlanNo.Text = ""
        txtSerialNumber.SelectAll()
        lblStateName.Text = ""
        btnStart.Enabled = False

        Me.Update()

        Dim selected_process As String = UCase(lblProcessName.Text)
        'selected_process = UCase(Process.ProcessName)

        Dim processInfo As ProcessInfo = ProcessService.GetProcessInfoByBarcode(Me.txtSerialNumber.Text, selected_process)
        'Dim workInfo As WorkInfo = ProductService.getWorkInfoFromBarcode(txtSerialNumber.Text)


        'MsgBoxUtils.Err(processInfo.barcode)


        'Dim process As ProcessInfo = ProcessService.GetProcessInfo(Me.AppData.Process.ProductPlanNo, Me.AppData.Process.ProcessComponentNo)

        If processInfo Is Nothing Then
            'It continues in the previous value
            'Process = Me.AppData.Process
            MsgBoxUtils.Err("Not valid barcode")
            lblProductName.Text = "--- Not valid barcode"
            Me.AppData.Process = Nothing
            'Me.AppData.SerialNoInfo.SerialNo = Me.txtSerialNumber.Text

            Me.AppData.Process.barcode = Me.txtSerialNumber.Text




        Else
            Me.AppData.Process = processInfo
            'Me.AppData.SerialNoInfo.SerialNo = Me.txtSerialNumber.Text

            Me.AppData.Process.barcode = Me.txtSerialNumber.Text

            lblProductName.Text = processInfo.ProductName
            lblProductPlanNo.Text = processInfo.ProductPlanNo
            'lblStateName.Text = processInfo.Status
            lblStateName.Text = ProcessProductStatus.GetName(processInfo.Status)
            'lblStateName.Text = ProcessProductStatus.GetName(0)
            'MsgBoxUtils.Err(processInfo.Status)


            If processInfo.Status = ProcessProductStatus.START Or processInfo.Status = ProcessProductStatus.COMPLETE Then
                Me.btnStart.Enabled = False
            Else
                Me.btnStart.Enabled = True
            End If


            Dim specProcesses As List(Of SpecInfo) = ProcessService.GetSpecInfo(processInfo.ProductPlanNo, processInfo.ProcessComponentNo)

            'Me.AppData.SerialNoInfo = Me.GetSerialNoInfo(Process.ProductPlanNo, Process.ProcessComponentNo, Me.txtSerialNumber.Text)
            Me.AppData.SerialNoInfo = Me.GetSerialNoInfo(processInfo.ProductPlanNo, processInfo.ProcessComponentNo, processInfo.SerialNo)


            If specProcesses IsNot Nothing Then
                If specProcesses.Count > 0 Then

                    Me.AppData.SpecProcesses = specProcesses
                    'MsgBoxUtils.Err(specProcesses.Count)
                    'Me.MoveSpecProduct()
                    Return
                End If

            Else
                MsgBoxUtils.Err("There is no spec for this product!")
            End If

        End If
        Me.txtSerialNumber.SelectAll()
        'lblProductName.Text = ""
        'lblProductPlanNo.Text = ""
        'lblStateName.Text = ""
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
        'lblProductName.Text = "-- Searching ---"
        'lblProductPlanNo.Text = ""
        'txtSerialNumber.Text = ""
        'lblStateName.Text = ""
        btnStart.Enabled = False

        Me.Update()

        Me.lblStateName.Text = ProcessProductStatus.GetName(1)


        Try
            Dim process As ProcessInfo = Me.AppData.Process
            Dim serialNoInfo As New SerialNoInfo()

            'If String.IsNullOrEmpty(Me.txtSerialNumber.Text) Then
            '    serialNoInfo.SerialNo = Me.CreateSerialNo(process.ProductPlanNo, process.ProcessComponentNo, Me.txtSerialNumber.Text)
            'Else
            '    serialNoInfo = Me.GetSerialNoInfo(process.ProductPlanNo, process.ProcessComponentNo, Me.txtSerialNumber.Text)
            'End If

            'Dim isExists As Boolean = _
            '    ProductService.ExistsPreviousResult(process.ProductPlanNo, process.ProcessComponentNo, serialNoInfo.SerialNo)

            'If Not isExists Then
            '    If MsgBoxUtils.YesNo(My.Resources.ErrorNoResultsPreviousProcess) = MsgBoxResult.No Then
            '        Return
            '    End If
            'End If

            If process.Status = -1 Then
                process.ProcessComponentNo = process.ProcessComponentNo + 1

            End If

            Me.WorkStart2(process)



            'Me.AppData.SerialNoInfo = Me.GetSerialNoInfo(process.ProductPlanNo, process.ProcessComponentNo, process.SerialNo)

            'If Not Me.AppData.IsMaterialInputCompletion() Then
            '    Me.MoveMaterialRead()
            '    Return
            'End If

            'If Not Me.AppData.IsSpecProductInputCompletion() Then
            '    Me.MoveSpecProduct()
            '    Return
            'End If

            'Me.RefreshProductInfo()
            'lblProductName.Text = ""
            'lblProductPlanNo.Text = ""
            'lblStateName.Text = ""

            'txtSerialNumber.Text = ""
            'Me.Update()


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

            'Me.lblStateName.Text = My.Resources.ProcessProductStatusStart
            Me.lblStateName.Text = ProcessProductStatus.GetName(process.Status)

            If Not isStart Then
                Throw New MesPdException(My.Resources.ErrorCanNotStart)
            Else

                'Me.lblStateName.Text = ProcessProductStatus.GetName(Me.AppData.SerialNoInfo.Status)
                'Me.lblStateName.Text = ProcessProductStatus.GetName(process.Status)
                'Me.lblStateName.Text = My.Resources.ProcessProductStatusStart
            End If
        End If
    End Sub


    Private Sub WorkStart2(ByVal process As ProcessInfo)

        If process.Status <> ProcessProductStatus.START Then

            Dim isStart As Boolean = ProductService.WorkStart2(process.ProductPlanNo, process.ProcessComponentNo, process.barcode)

            'Me.lblStateName.Text = My.Resources.ProcessProductStatusStart
            'Me.lblStateName.Text = ProcessProductStatus.GetName(process.Status)

            If Not isStart Then
                Throw New MesPdException(My.Resources.ErrorCanNotStart)
            Else

                'Me.lblStateName.Text = ProcessProductStatus.GetName(Me.AppData.SerialNoInfo.Status)
                'Me.lblStateName.Text = ProcessProductStatus.GetName(process.Status)
                'Me.lblStateName.Text = My.Resources.ProcessProductStatusStart
                'Me.lblStateName.Text = ProcessProductStatus.GetName(1)
                Me.lblStateName.Text = ProcessProductStatus.GetName(ProcessProductStatus.START)
                'Me.AppData.Process.Status = ProcessProductStatus.START

                'Me.UpdateBarcode()

            End If
        End If
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
        'MsgBoxUtils.Err(data)
        Try

            lblProductName.Text = "-- Searching ---"
            lblProductPlanNo.Text = ""
            txtSerialNumber.Text = ""
            lblStateName.Text = ""
            btnStart.Enabled = False

            Me.Update()


            If String.IsNullOrEmpty(data) Then Return

            Me.txtSerialNumber.Text = StringUtils.TruncateLength(data, Me.txtSerialNumber.MaxLength)

            'Dim process As ProcessInfo = Me.AppData.Process

            'Me.AppData.SerialNoInfo = Me.GetSerialNoInfo(process.ProductPlanNo, process.ProcessComponentNo, Me.txtSerialNumber.Text)
            'Me.RefreshProductInfo()

            'Me.UpdateBarcode()

            'MsgBoxUtils.Err("XXXXXX")

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

        'Me.txtSerialNumber.Text = Me.AppData.SerialNoInfo.SerialNo
        txtSerialNumber.Text = Me.AppData.Process.barcode
        'Me.lblStateName.Text = ProcessProductStatus.GetName(Me.AppData.SerialNoInfo.Status)
        Me.lblStateName.Text = ProcessProductStatus.GetName(process.Status)

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

        'MsgBoxUtils.Err(Me.AppData.SerialNoInfo.SpecProducts.Count)

        'If Me.AppData.SerialNoInfo.SpecProducts.Count = 0 Then Return
        If Me.AppData.SerialNoInfo.SpecProducts.Count = 0 Then Return
        'Me.AppData.SpecProcesses = specProcesses


        Me.AppData.NowSpecType = MESPdHandy.AppData.SPEC_TYPE.PRODUCT

        Dim spec As SpecInfo = DisplayOrderUtils.FirstOrder(Me.AppData.SerialNoInfo.SpecProducts)
        Me.AppData.NowDisplayOrder = spec.DisplayOrder

        Me.MoveNextForm(SpecFormUtils.GetSpecForm(spec.SpecAttributeId))
    End Sub


    Protected Overrides Sub btnF1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)
        Me.MoveNextForm(New frmProcessRead())
    End Sub

    Protected Overrides Sub btnF6_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)
        txtSerialNumber.Text = AppData.ForTest(0)
    End Sub

    Private Sub btnF3_Click_1(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnF3.Click
        'If Not Me.AppData.Process Is Nothing Then
        'Me.MoveNextForm(New frmSpecButton())
        'End If

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

    Private Sub txtSerialNumber_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles txtSerialNumber.TextChanged
        lblProductName.Text = ""
        lblProductPlanNo.Text = ""
        'txtSerialNumber.Text = ""
        lblStateName.Text = ""
        btnStart.Enabled = False
        Me.Update()

    End Sub
End Class
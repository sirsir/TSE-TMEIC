Imports Newtonsoft.Json

Namespace Service

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <remarks></remarks>
    Public NotInheritable Class ProcessService

        Private Sub New()
        End Sub

        ''' <summary>
        ''' 
        ''' </summary>
        ''' <param name="productPlanNo"></param>
        ''' <param name="processComponentNo"></param>
        ''' <returns></returns>
        ''' <remarks></remarks>
        Public Shared Function GetProcessInfo(ByVal productPlanNo As String, ByVal processComponentNo As Integer) As ProcessInfo

            Dim params As New Dictionary(Of String, Object)
            params.Add("productPlanNo", productPlanNo)
            params.Add("processComponentNo", processComponentNo)

            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/process/processInfo", params)
            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))

            If result("result") = "NG" Then Return Nothing

            Dim processInfo As ProcessInfo = JsonConvert.DeserializeAnonymousType(result("processInfo"), New ProcessInfo())

            Return processInfo

        End Function

        ''' <summary>
        ''' 
        ''' </summary>
        ''' <param name="productPlanNo"></param>
        ''' <param name="processComponentNo"></param>
        ''' <returns></returns>
        ''' <remarks></remarks>
        'Public Shared Function GetAllProcessHandy() As ProcessInfo
        'Public Shared Function GetAllProcessHandy() As List(Of Process)
        Public Shared Function GetAllProcessHandy() As List(Of ProcessInfo)
            'Public Shared Function GetAllProcessHandy() As String

            Dim params As New Dictionary(Of String, Object)
            'params.Add("productPlanNo", productPlanNo)
            'params.Add("processComponentNo", processComponentNo)

            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/process/processAllHandy", params)


            'Return json
            'MsgBox(json)

            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))

            If result("result") = "NG" Then Return Nothing

            'Dim processInfo As ProcessInfo = JsonConvert.DeserializeAnonymousType(result("processInfo"), New ProcessInfo())
            'Dim processInfo As List(Of Process) = JsonConvert.DeserializeObject(Of List(Of Process))(result("processInfo"))
            'Dim ProcessInfo As Process() = JsonConvert.DeserializeObject(Of List(Of Process))(result("processInfo")))
            'Dim ProcessInfo As Process() = JsonConvert.DeserializeAnonymousType(result("processInfo"), New Process())
            'Dim processInfo As Process() = JsonConvert.DeserializeAnonymousType(result("processInfo"), New Process())
            Dim process As List(Of ProcessInfo) = JsonConvert.DeserializeAnonymousType(result("processInfo"), New List(Of ProcessInfo))

            Return process

        End Function


        ''' <summary>
        ''' 
        ''' </summary>
        ''' <param name="barcode"></param>
        ''' <returns></returns>
        ''' <remarks></remarks>
        Public Shared Function GetProcessInfoByName(ByVal processName As String) As ProcessInfo

            Dim params As New Dictionary(Of String, Object)
            params.Add("processName", processName)

            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/process/processInfoByName", params)
            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))
            Debug.Write(result)


            If result("result") = "NG" Then Return Nothing

            Dim processInfo As ProcessInfo = JsonConvert.DeserializeAnonymousType(result("processInfo"), New ProcessInfo())

            Return processInfo

        End Function


        ''' <summary>
        ''' 
        ''' </summary>
        ''' <param name="barcode"></param>
        ''' <returns></returns>
        ''' <remarks></remarks>
        Public Shared Function GetProcessInfoByBarcode(ByVal barcode As String, ByVal selected_process As String) As ProcessInfo

            Dim params As New Dictionary(Of String, Object)
            params.Add("barcode", barcode)
            params.Add("selected_process", selected_process)


            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/process/processInfoByBarcode", params)
            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))

            If result("result") = "NG" Then Return Nothing

            Dim processInfo As ProcessInfo = JsonConvert.DeserializeAnonymousType(result("processInfo"), New ProcessInfo())

            Return processInfo

        End Function

        ''' <summary>
        ''' 
        ''' </summary>
        ''' <param name="productPlanNo"></param>
        ''' <param name="processComponentNo"></param>
        ''' <returns></returns>
        ''' <remarks></remarks>
        Public Shared Function GetSpecInfo(ByVal productPlanNo As String, ByVal processComponentNo As Integer) As List(Of SpecInfo)

            Dim params As New Dictionary(Of String, Object)
            params.Add("productPlanNo", productPlanNo)
            params.Add("processComponentNo", processComponentNo)

            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/process/specInfo", params)
            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))

            If result("result") = "NG" Then Return Nothing

            Dim specInfo As List(Of SpecInfo) = JsonConvert.DeserializeAnonymousType(result("specInfo"), New List(Of SpecInfo))

            Return specInfo

        End Function

        ''' <summary>
        ''' 
        ''' </summary>
        ''' <param name="productPlanNo"></param>
        ''' <param name="processComponentNo"></param>
        ''' <returns></returns>
        ''' <remarks></remarks>
        Public Shared Function ProcessWorkStart(ByVal productPlanNo As String, ByVal processComponentNo As Integer) As Boolean

            Dim params As New Dictionary(Of String, Object)
            params.Add("productPlanNo", productPlanNo)
            params.Add("processComponentNo", processComponentNo)

            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/process/processWorkStart", params)
            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))

            If result("result") = "NG" Then Return False

            Return BooleanUtils.Parse(result("isStart"))

        End Function

        ''' <summary>
        ''' 
        ''' </summary>
        ''' <param name="productPlanNo"></param>
        ''' <param name="processComponentNo"></param>
        ''' <returns></returns>
        ''' <remarks></remarks>
        Public Shared Function ProcessWorkFinish(ByVal productPlanNo As String, ByVal processComponentNo As Integer) As Boolean

            Dim params As New Dictionary(Of String, Object)
            params.Add("productPlanNo", productPlanNo)
            params.Add("processComponentNo", processComponentNo)

            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/process/processWorkFinish", params)
            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))

            If result("result") = "NG" Then Return False

            Return BooleanUtils.Parse(result("isComplet"))

        End Function

        ''' <summary>
        ''' 
        ''' </summary>
        ''' <param name="productPlanNo"></param>
        ''' <param name="processComponentNo"></param>
        ''' <param name="spec"></param>
        ''' <remarks></remarks>
        Public Shared Sub SpecRegister( _
            ByVal productPlanNo As String, ByVal processComponentNo As Integer, ByVal spec As List(Of SpecInfo))

            Dim params As New Dictionary(Of String, Object)
            params.Add("productPlanNo", productPlanNo)
            params.Add("processComponentNo", processComponentNo)
            params.Add("specs", JsonConvert.SerializeObject(spec))

            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/process/specRegister", params)
            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))

            If result("result") = "NG" Then
                Throw New MesPdException(My.Resources.ErrorRegister)
            End If
        End Sub

    End Class

End Namespace

Imports Newtonsoft.Json

Namespace Service

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <remarks></remarks>
    Public NotInheritable Class ProductService

        Private Sub New()
        End Sub

        ''' <summary>
        ''' 
        ''' </summary>
        ''' <param name="productPlanNo"></param>
        ''' <param name="processComponentNo"></param>
        ''' <param name="serialNo"></param>
        ''' <returns></returns>
        ''' <remarks></remarks>
        Public Shared Function GetSerialNoInfo( _
            ByVal productPlanNo As String, ByVal processComponentNo As Integer, ByVal serialNo As String) As SerialNoInfo

            Dim params As New Dictionary(Of String, Object)
            params.Add("productPlanNo", productPlanNo)
            params.Add("processComponentNo", processComponentNo)
            params.Add("serialNo", serialNo)

            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/product/serialNoInfo", params)
            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))

            If result("result") = "NG" Then Return Nothing

            Dim serialNoInfo As New SerialNoInfo()
            serialNoInfo.SerialNo = serialNo
            serialNoInfo.Status = NumberUtils.Parse(result("status"))
            serialNoInfo.Materials = JsonConvert.DeserializeAnonymousType(result("materialInfo"), New List(Of MaterialInfo))
            serialNoInfo.SpecProducts = JsonConvert.DeserializeAnonymousType(result("specInfo"), New List(Of SpecInfo))

            Return serialNoInfo

        End Function


        ''' <summary>
        ''' 
        ''' </summary>
        ''' <param name="productPlanNo"></param>
        ''' <param name="processComponentNo"></param>
        ''' <param name="serialNo"></param>
        ''' <returns></returns>
        ''' <remarks></remarks>
        Public Shared Function getWorkInfoFromBarcode(ByVal barcode As String) As WorkInfo

            Dim params As New Dictionary(Of String, Object)

            params.Add("barcode", barcode)

            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/product/getWorkInfoFromBarcode", params)
            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))

            If result("result") = "NG" Then Return Nothing

            Dim workInfo As New WorkInfo()
            workInfo.Barcode = barcode
            workInfo.ProductName = result("productName")
            workInfo.ProductPlanNo = result("ProductPlanNo")
            workInfo.ProcessName = result("ProcessName")
            workInfo.Status = result("Status")
            workInfo.ProductName = result("productName")
            workInfo.SpecProducts = JsonConvert.DeserializeAnonymousType(result("specInfo"), New List(Of SpecInfo))

            Return workInfo

        End Function

        Public Shared Function GetWork( _
            ByVal barcode As String) As WorkInfo

            Dim params As New Dictionary(Of String, Object)
            params.Add("barcode", barcode)

            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/product/barcodeInfo", params)
            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))

            If result("result") = "NG" Then Return Nothing

            Dim workInfo As New WorkInfo()
            workInfo.Barcode = barcode
            workInfo.Status = NumberUtils.Parse(result("status"))
            'workInfo.Materials = JsonConvert.DeserializeAnonymousType(result("materialInfo"), New List(Of MaterialInfo))
            'workInfo.SpecProducts = JsonConvert.DeserializeAnonymousType(result("specInfo"), New List(Of SpecInfo))

            Return workInfo

        End Function
        ''' <summary>
        ''' 
        ''' </summary>
        ''' <param name="productPlanNo"></param>
        ''' <param name="processComponentNo"></param>
        ''' <param name="serialNo"></param>
        ''' <returns></returns>
        ''' <remarks></remarks>
        Public Shared Function CreateSerialNo( _
            ByVal productPlanNo As String, ByVal processComponentNo As Integer, ByVal serialNo As String) As String

            Dim params As New Dictionary(Of String, Object)
            params.Add("productPlanNo", productPlanNo)
            params.Add("processComponentNo", processComponentNo)
            params.Add("serialNo", serialNo)

            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/product/createSerialNo", params)
            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))

            If result("result") = "NG" Then

                Dim errorId As String = result("errorId")

                If errorId = "SerialRequired" Then
                    Throw New MesPdException(My.Resources.ErrorSerialRequired)
                End If

                Return Nothing
            End If

            Return result("serialNo")

        End Function

        ''' <summary>
        ''' 
        ''' </summary>
        ''' <param name="productPlanNo"></param>
        ''' <param name="processComponentNo"></param>
        ''' <param name="serialNo"></param>
        ''' <returns></returns>
        ''' <remarks></remarks>
        Public Shared Function ExistsPreviousResult( _
            ByVal productPlanNo As String, ByVal processComponentNo As Integer, ByVal serialNo As String) As Boolean

            Dim params As New Dictionary(Of String, Object)
            params.Add("productPlanNo", productPlanNo)
            params.Add("processComponentNo", processComponentNo)
            params.Add("serialNo", serialNo)

            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/product/existsPreviousResult", params)
            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))

            If result("result") = "NG" Then Return False

            Return BooleanUtils.Parse(result("isExists"))

        End Function

        ''' <summary>
        ''' 
        ''' </summary>
        ''' <param name="productPlanNo"></param>
        ''' <param name="processComponentNo"></param>
        ''' <param name="serialNo"></param>
        ''' <returns></returns>
        ''' <remarks></remarks>
        Public Shared Function WorkStart( _
            ByVal productPlanNo As String, ByVal processComponentNo As Integer, ByVal serialNo As String) As Boolean

            Dim params As New Dictionary(Of String, Object)
            params.Add("productPlanNo", productPlanNo)
            params.Add("processComponentNo", processComponentNo)
            params.Add("serialNo", serialNo)

            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/product/workStart", params)
            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))

            If result("result") = "NG" Then Return False

            Return BooleanUtils.Parse(result("isStart"))

        End Function


        Public Shared Function WorkStart2( _
            ByVal productPlanNo As String, ByVal processComponentNo As Integer, ByVal barcode As String) As Boolean

            Dim params As New Dictionary(Of String, Object)
            params.Add("productPlanNo", productPlanNo)
            params.Add("processComponentNo", processComponentNo)
            params.Add("barcode", barcode)
            'params.Add("processComponentNo", processComponentNo)
            'params.Add("serialNo", serialNo)

            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/product/workStart2", params)
            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))

            If result("result") = "NG" Then Return False

            Return BooleanUtils.Parse(result("isStart"))

        End Function

        ''' <summary>
        ''' 
        ''' </summary>
        ''' <param name="productPlanNo"></param>
        ''' <param name="processComponentNo"></param>
        ''' <param name="serialNo"></param>
        ''' <param name="user"></param>
        ''' <returns></returns>
        ''' <remarks></remarks>
        Public Shared Function WorkEnd( _
            ByVal productPlanNo As String, ByVal processComponentNo As Integer, ByVal serialNo As String, ByVal user As UserInfo) As Boolean



            Dim params As New Dictionary(Of String, Object)
            params.Add("productPlanNo", productPlanNo)
            params.Add("processComponentNo", processComponentNo)
            params.Add("serialNo", serialNo)
            params.Add("userId", user.UserId)
            params.Add("userName", user.UserName)

            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/product/workFinish", params)
            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))

            If result("result") = "NG" Then Return False

            Return BooleanUtils.Parse(result("isComplet"))

        End Function

        ''' <summary>
        ''' 
        ''' </summary>
        ''' <param name="productPlanNo"></param>
        ''' <param name="processComponentNo"></param>
        ''' <param name="serialNo"></param>
        ''' <param name="material"></param>
        ''' <remarks></remarks>
        Public Shared Sub MaterialRegister( _
            ByVal productPlanNo As String, ByVal processComponentNo As Integer, ByVal serialNo As String, ByVal material As MaterialInfo)

            Dim params As New Dictionary(Of String, Object)
            params.Add("productPlanNo", productPlanNo)
            params.Add("processComponentNo", processComponentNo)
            params.Add("serialNo", serialNo)
            params.Add("materialComponentNo", material.MaterialComponentNo)
            params.Add("materialQty", material.MaterialQty)

            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/product/materialRegister", params)
            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))

            If result("result") = "NG" Then
                Throw New MesPdException(My.Resources.ErrorRegister)
            End If
        End Sub

        ''' <summary>
        ''' 
        ''' </summary>
        ''' <param name="productPlanNo"></param>
        ''' <param name="processComponentNo"></param>
        ''' <param name="serialNo"></param>
        ''' <param name="spec"></param>
        ''' <remarks></remarks>
        Public Shared Sub SpecRegister( _
            ByVal productPlanNo As String, ByVal processComponentNo As Integer, ByVal serialNo As String, ByVal spec As List(Of SpecInfo))

            Dim params As New Dictionary(Of String, Object)
            params.Add("productPlanNo", productPlanNo)
            params.Add("processComponentNo", processComponentNo)
            params.Add("serialNo", serialNo)
            params.Add("specs", JsonConvert.SerializeObject(spec))

            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/product/specRegister", params)
            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))

            If result("result") = "NG" Then
                Throw New MesPdException(My.Resources.ErrorRegister)
            End If
        End Sub

        ''' <summary>
        ''' 
        ''' </summary>
        ''' <param name="serialNo"></param>
        ''' <returns></returns>
        ''' <remarks></remarks>
        Public Shared Function InterimProductId(ByVal serialNo As String) As String

            Dim params As New Dictionary(Of String, Object)
            params.Add("serialNo", serialNo)

            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/product/interimProductId", params)
            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))

            If result("result") = "NG" Then
                Return String.Empty
            End If

            Return result("productId")

        End Function

    End Class

End Namespace

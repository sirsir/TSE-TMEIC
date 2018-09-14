Imports Newtonsoft.Json

Namespace Service

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <remarks></remarks>
    Public NotInheritable Class UserService

        Private Sub New()
        End Sub

        ''' <summary>
        ''' Get Password Required Setting
        ''' </summary>
        ''' <returns></returns>
        ''' <remarks></remarks>
        Public Shared Function IsPasswordRequired() As Boolean

            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/login/passwordRequired")
            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))

            If result("result") = "OK" Then
                Return BooleanUtils.Parse(result("required"))
            End If

            Return True

        End Function

        ''' <summary>
        ''' 
        ''' </summary>
        ''' <param name="userCode"></param>
        ''' <param name="password"></param>
        ''' <returns></returns>
        ''' <remarks></remarks>
        Public Shared Function IsAuth(ByVal userCode As String, ByVal password As String) As Boolean

            Dim params As New Dictionary(Of String, Object)
            params.Add("userCode", userCode)
            params.Add("password", password)

            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/login/login", params)
            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))

            If result("result") = "OK" Then Return True

            Return False

        End Function

        ''' <summary>
        ''' 
        ''' </summary>
        ''' <param name="userCode"></param>
        ''' <param name="password"></param>
        ''' <returns></returns>
        ''' <remarks></remarks>
        Public Shared Function GetUserInfo(ByVal userCode As String, ByVal password As String) As UserInfo

            Dim params As New Dictionary(Of String, Object)
            params.Add("userCode", userCode)
            params.Add("password", password)

            Dim json As String = HttpUtils.GetRequest(AppData.RootUrl & "/handy/login/userInfo", params)
            Dim result As Dictionary(Of String, String) = JsonConvert.DeserializeAnonymousType(json, New Dictionary(Of String, String))

            If result("result") = "NG" Then Return Nothing

            Dim userInfo As UserInfo = JsonConvert.DeserializeAnonymousType(result("userInfo"), New UserInfo())

            Return userInfo

        End Function

    End Class

End Namespace
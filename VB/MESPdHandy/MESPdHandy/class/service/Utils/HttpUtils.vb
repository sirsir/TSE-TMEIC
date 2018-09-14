Imports System.Net
Imports System.IO

''' <summary>
''' HTTP Utility
''' </summary>
''' <remarks></remarks>
Public NotInheritable Class HttpUtils

    Private Sub New()
    End Sub


    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="url"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function GetRequest(ByVal url As String) As String

        If String.IsNullOrEmpty(url) Then Return String.Empty

        Dim request As HttpWebRequest = WebRequest.Create(url)
        request.Proxy = Nothing

        Dim result As String = String.Empty

        Using response As HttpWebResponse = request.GetResponse()
            Using stream As Stream = response.GetResponseStream()
                Using reader As New StreamReader(stream)
                    result = reader.ReadToEnd()
                End Using
            End Using
        End Using

        Return result

    End Function

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <param name="url"></param>
    ''' <param name="params"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function GetRequest(ByVal url As String, ByVal params As Dictionary(Of String, Object)) As String

        Dim paramString As New List(Of String)

        For Each key As String In params.Keys
            paramString.Add(String.Format("{0}={1}", key, params(key).ToString()))
        Next

        Return GetRequest(url & "?" & String.Join("&", paramString.ToArray()))

    End Function

End Class

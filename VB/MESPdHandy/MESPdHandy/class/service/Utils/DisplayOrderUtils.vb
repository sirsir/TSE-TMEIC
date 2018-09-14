''' <summary>
''' DisplayOrder Utility
''' </summary>
''' <remarks></remarks>
Public NotInheritable Class DisplayOrderUtils

    Private Sub New()
    End Sub

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <typeparam name="t"></typeparam>
    ''' <param name="value"></param>
    ''' <param name="order"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function SelectOrder(Of t As DisplayOrder)(ByVal value As List(Of t), ByVal order As Integer) As t

        Return (From e In value _
                Where e.DisplayOrder.Value = order _
                Select e _
                ).First()
    End Function

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <typeparam name="t"></typeparam>
    ''' <param name="value"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function FirstOrder(Of t As DisplayOrder)(ByVal value As List(Of t)) As t

        Dim order = Aggregate e In value Into Min(e.DisplayOrder)

        Return SelectOrder(value, order)
    End Function

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <typeparam name="t"></typeparam>
    ''' <param name="value"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function LastOrder(Of t As DisplayOrder)(ByVal value As List(Of t)) As t

        Dim order = Aggregate e In value Into Max(e.DisplayOrder)

        Return SelectOrder(value, order)
    End Function

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <typeparam name="t"></typeparam>
    ''' <param name="value"></param>
    ''' <param name="nowOrder"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function NextOrder(Of t As DisplayOrder)(ByVal value As List(Of t), ByVal nowOrder As Integer) As t

        Dim order = Aggregate e In value Where e.DisplayOrder > nowOrder Into Min(e.DisplayOrder)

        If order Is Nothing Then
            Return FirstOrder(value)
        End If

        Return SelectOrder(value, order)
    End Function

    ''' <summary>
    ''' 
    ''' </summary>
    ''' <typeparam name="t"></typeparam>
    ''' <param name="value"></param>
    ''' <param name="nowOrder"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Shared Function PrevOrder(Of t As DisplayOrder)(ByVal value As List(Of t), ByVal nowOrder As Integer) As t

        Dim order = Aggregate e In value Where e.DisplayOrder < nowOrder Into Max(e.DisplayOrder)

        If order Is Nothing Then
            Return LastOrder(value)
        End If

        Return SelectOrder(value, order)
    End Function

End Class

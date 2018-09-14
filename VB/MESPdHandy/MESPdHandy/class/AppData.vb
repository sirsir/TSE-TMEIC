Public Class AppData

    Public Enum SPEC_TYPE
        PROCESS
        PRODUCT
    End Enum

    

    'Public ForTest As String() = {"000004"}
    Public ForTest As String() = {""}
   

    ''' <summary>Root</summary>
    Public Shared RootUrl As String = ""

    ''' <summary>User</summary>
    Public User As New UserInfo

    ''' <summary>Process</summary>
    Public Process As New ProcessInfo

    ''' <summary>SerialNo Info</summary>
    Public SerialNoInfo As New SerialNoInfo

    ''' <summary>Spec Process</summary>
    Public SpecProcesses As New List(Of SpecInfo)

    ''' <summary>NowSpecType</summary>
    Public NowSpecType As SPEC_TYPE

    ''' <summary>NowDisplayOrder</summary>
    Public NowDisplayOrder As Integer

    ''' <summary>
    ''' Check the already input all material
    ''' </summary>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Function IsMaterialInputCompletion() As Boolean

        For Each material As MaterialInfo In SerialNoInfo.Materials
            If (material.MaterialQty Is Nothing) Then Return False
            If (material.MaterialQty = 0) Then Return False
        Next

        Return True

    End Function

    ''' <summary>
    ''' Check the already input all spec process
    ''' </summary>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Function IsSpecProcessInputCompletion() As Boolean

        For Each spec As SpecInfo In SpecProcesses
            If String.IsNullOrEmpty(spec.InputValue) Then Return False
        Next

        Return True

    End Function

    ''' <summary>
    ''' Check the already input all spec product
    ''' </summary>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Function IsSpecProductInputCompletion() As Boolean

        For Each spec As SpecInfo In SerialNoInfo.SpecProducts
            If String.IsNullOrEmpty(spec.InputValue) Then Return False
        Next

        Return True

    End Function

    ''' <summary>
    ''' Get SpecInfo
    ''' </summary>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Function SpecInfo() As List(Of SpecInfo)

        Select Case NowSpecType
            Case SPEC_TYPE.PROCESS
                Return Me.SpecProcesses

            Case SPEC_TYPE.PRODUCT
                Return Me.SerialNoInfo.SpecProducts

        End Select

        Return New List(Of SpecInfo)

    End Function

End Class

Public NotInheritable Class SpecFormUtils

    Public Shared Function GetSpecForm(ByVal attributeId As Integer) As frmSpec

        Select Case attributeId
            Case SpecAttributeId.TEXT, SpecAttributeId.NUMBER
                Return New frmSpecInput()

            Case SpecAttributeId.BUTTON, SpecAttributeId.PASS_OR_FAIL
                Return New frmSpecButton()

            Case SpecAttributeId.PULLDOWN
                Return New frmSpecSelect()

        End Select

        Return Nothing

    End Function

End Class

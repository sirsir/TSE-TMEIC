Imports Calib.IMGLibNet.Api
Imports Calib.IMGLibNet.Def
Imports Calib.SystemLibNet.Def

Public Class ScanCodeFactory

    Public Shared Function ScanCode() As ScanCodeImpl

        Dim result = IMGGetDecodeMode(0, 0, "")

        If result = IMG_ERR_NOTINITIALIZED Then
            Return New ScanImg()
        End If

        Return New ScanLaser()

    End Function

End Class

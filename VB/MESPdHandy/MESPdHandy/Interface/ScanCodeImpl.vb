Public Interface ScanCodeImpl

    Sub Connect()

    Sub Disconnect()

    Sub Decode()

    Sub ScanStart()

    Sub ScanStop()

    Function GetScanData() As String

End Interface

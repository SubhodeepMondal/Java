class bank :
    def __init__(self, IFSC_Code, bankname, branchname, loc):
        self.IFSC_Code = IFSC_Code
        self.bankname = bankname
        self.branchname = branchname
        self.loc = locals()
        
class account(bank) :
    def __init__(self,AccountID, customer, balance,IFSC_Code, bankname, branchname, loc):
        self.AccountID = AccountID
        self.customer = customer
        self.balance = balance
        bank.__init__(self,IFSC_Code,bankname, branchname, loc)
        
    def getAccountInfo(self):
        print("Bank Name:", self.bankname,"\nBranch Name ", self.branchname,"\nIFSC Code: ", self.IFSC_Code)
        print("Account ID: ", self.AccountID,"\nBalance: ", self.balance, "\n")
        
    def withdraw(self,amount):
        self.balance -= amount
            
    def deposit(self,amount,trans_cond):
        if trans_cond == 'true' :
            self.balance += amount
            
    def getBalance(self):
        print("Acc Balance: ", self.balance)
        
        
class customer :
    def __init__(self,CustomerID,custname,address,contactdetails):
        self.CustomerID = CustomerID
        self.custname = custname
        self.address = address
        self.contactdetails = contactdetails
        
    def customerDetails(self):
        print("Customer Name: ", self.custname,"\nCustomer ID: ", self.CustomerID,"\nAddress: ", self.address,"\nContact Details: ",self.contactdetails, "\n")


cust = customer('C0001','Sanjay','Kolkata','729791112')
custAcc = account('A0001','C0001',0,'IFSC0001','Express Bank','Town Ship Branch', 'Town Ship')
custAcc.getAccountInfo() 
custAcc.deposit(2000,'true') 
custAcc.withdraw(500) 
custAcc.getBalance()

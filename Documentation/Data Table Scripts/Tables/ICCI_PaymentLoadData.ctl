LOAD DATA
INFILE 'C:\Users\mateo_406767\Desktop\Proyecto_Grupo3\Tables\ICCI_Payment.csv'
REPLACE
INTO TABLE guest121.ICCI_PAYMENT
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS
(	PAYMENTID INTEGER EXTERNAL,
	CARDNO INTEGER EXTERNAL,
	PAYMENTTYPE,
	AMOUNTPAID DECIMAL EXTERNAL,
	DATEOFPAYMENT DATE
)
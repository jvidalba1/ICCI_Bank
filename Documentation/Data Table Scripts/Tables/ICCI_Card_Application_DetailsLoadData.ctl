LOAD DATA
INFILE 'C:\Users\mateo_406767\Desktop\Proyecto_Grupo3\Tables\ICCI_Card_Application_Details.csv'
REPLACE
INTO TABLE guest121.ICCI_Card_Application_Details
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS
(	APPLICATIONID INTEGER EXTERNAL,
	NAME,
	ADDRESS,
	EMAIL,
	PHONE,
	DATEOFAPPLICATION DATE
)
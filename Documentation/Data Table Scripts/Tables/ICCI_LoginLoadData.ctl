LOAD DATA
INFILE 'C:\Users\mateo_406767\Desktop\Proyecto_Grupo3\Tables\ICCI_Login.csv'
REPLACE
INTO TABLE guest121.ICCI_LOGIN
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS
(	username,
	password
)

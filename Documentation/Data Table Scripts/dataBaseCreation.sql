drop table icci_login cascade constraints;
drop table icci_scheme cascade constraints;
drop table icci_card_application_details cascade constraints;
drop table icci_customer cascade constraints;
drop table icci_card cascade constraints;
drop table icci_payment cascade constraints;
drop table icci_transaction cascade constraints;
drop table icci_blockedcard cascade constraints;

drop sequence seq_blockid;
drop sequence seq_applicationid;
drop sequence seq_customerid;
drop sequence seq_paymentid;
drop sequence seq_transactionid;

drop function sf_checkCardStatus;

create table icci_login(
username varchar2(15) constraint icci_login_pk primary key,
password varchar2(15) not null
);

create table icci_scheme(
schemeid char(1) constraint icci_scheme_pk primary key,
schemeamount number(7,2) not null,
minimumamount number(7,2) not null,
interestrate number(4,2)
);

create table icci_card_application_details(
applicationid number(5) constraint icci_card_details_pk primary key,
name varchar2(20) not null,
address varchar2(40) not null,
email varchar2(20),
phone varchar2(10),
dateofapplication date not null
);

create table icci_customer(
customerid number(5) constraint icci_customer_pk primary key,
name varchar2(20),
address varchar2(40),
phone varchar2(10),
email varchar2(20),
username varchar2(15),
constraint icci_customer_fk1 foreign key (username) references icci_login(username)
);

create table icci_card(
cardno number(16) constraint icci_card_pk primary key,
pin number(4) not null,
schemeid char(1),
balanceamount number(7,2),
cardamount number(7,2),
dateofregistration date,
customerid number(5),
constraint icci_card_fk2 foreign key (customerid) references icci_customer(customerid),
constraint icci_card_fk1 foreign key (schemeid) references icci_scheme(schemeid)
);

create table icci_payment(
paymentid number(5) constraint icci_payment_pk primary key,
cardno number(16),
paymenttype char(1) constraint icci_payment_ck_paymenttype check (paymenttype in('F','M','B')),
amountpaid number(7,2),
dateofpayment date,
constraint icci_payment_fk1 foreign key (cardno) references icci_card(cardno)
);

create table icci_transaction(
transactionid number(5) constraint icci_transaction_pk primary key,
cardno number(16),
description varchar2(20) not null,
amount number(7,2),
dateoftransaction date,
constraint icci_transaction_fk1 foreign key (cardno) references icci_card(cardno)
);

create table icci_blockedcard(
blockid number(4) constraint icci_blockedcard_pk primary key,
cardno number(16),
dateofblock date,
description varchar2(20),
status char(1) constraint icci_blockedcard_ck_status check(status in ('B', 'N')),
constraint icci_blockedcard_fk1 foreign key (cardno) references icci_card(cardno)
);

create sequence seq_blockid start with 101 increment by 1;
create sequence seq_applicationid start with 40006 increment by 1;
create sequence seq_customerid start with 20006 increment by 1;
create sequence seq_paymentid start with 6005 increment by 1;
create sequence seq_transactionid start with 9004 increment by 1;

CREATE OR REPLACE function sf_checkCardStatus(p_cardnumber IN NUMBER)
RETURN NUMBER IS
p_card number;
p_status number;
BEGIN
        SELECT count(cardno)
        into p_card
        from icci_card
        where cardno = p_cardnumber;
        
        if (p_card = 0) then
                return 1;
        end if;
        
        SELECT count(cardno)
        into p_status
        from icci_blockedcard
        where status='B'
        and cardno = p_cardnumber;

        if(p_status = 0) then 
                return 2;
        end if;

        return 3;                       
                
EXCEPTION
        when others then
                return 0;       
END;
/


sqlldr guest121/infy@georli04 control=C:\Users\Mateo_406767\Desktop\Proyecto_Grupo3\Tables\ICCI_LoginLoadData.ctl
sqlldr guest121/infy@georli04 control=C:\Users\Mateo_406767\Desktop\Proyecto_Grupo3\Tables\ICCI_SchemeLoadData.ctl
sqlldr guest121/infy@georli04 control=C:\Users\Mateo_406767\Desktop\Proyecto_Grupo3\Tables\ICCI_Card_Application_Details.ctl
sqlldr guest121/infy@georli04 control=C:\Users\Mateo_406767\Desktop\Proyecto_Grupo3\Tables\ICCI_CustomerLoadData.ctl
sqlldr guest121/infy@georli04 control=C:\Users\Mateo_406767\Desktop\Proyecto_Grupo3\Tables\ICCI_CardLoadData.ctl
sqlldr guest121/infy@georli04 control=C:\Users\Mateo_406767\Desktop\Proyecto_Grupo3\Tables\ICCI_PaymentLoadData.ctl
sqlldr guest121/infy@georli04 control=C:\Users\Mateo_406767\Desktop\Proyecto_Grupo3\Tables\ICCI_TransactionLoadData.ctl
sqlldr guest121/infy@georli04 control=C:\Users\Mateo_406767\Desktop\Proyecto_Grupo3\Tables\ICCI_BlockedCardLoadData.ctl

#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Tue Aug  7 10:55:00 2018

@author: ssrinivasan.19
"""

from datetime import timedelta, date
import quandl
import pandas
from pandas import DataFrame as df
import csv

def daterange(date1, date2):
    for n in range(int ((date2 - date1).days)+1):
        yield date1 + timedelta(n)
        

input_start_date = date(2008, 01, 01)
input_end_date = date(2008, 06, 30)

with open('GoldPricesFactors.csv', 'w') as f:
    writer = csv.writer(f)
    writer.writerow(['Date','BSE','Dow Jones','NASDAQ','Crude Oil price','USD/EURO',
             'USD/YEN','USD/GBP','USD/INR','USD/CAD','GOLD PRICE'])

i = 0


for input_date in daterange(input_start_date , input_end_date ):

    #Initializing the variables Begin
    bse_value = 0
    dow_jones_value = 0
    nasdaq_value = 0
    crude_oil_value = 0
    euro_value = 0
    yen_value = 0
    gbp_value = 0
    inr_value = 0
    cad_value = 0
    gold_price_value = 0
    #Initializing the variables End

    input_date = input_date.strftime("%d-%m-%Y")
    
    #BSE SENSEX
    bse_value = quandl.get("BSE/SENSEX.4", authtoken="d-S-cB5976zhjoB4o-hm", start_date=input_date, end_date = input_end_date)
    if not bse_value.empty:
        bse_value = bse_value.iat[0,0]
            
    #DOW JONES INDEX
    dow_jones_value = quandl.get("BCB/7809.1", authtoken="d-S-cB5976zhjoB4o-hm", start_date=input_date, end_date = input_end_date)
    if not dow_jones_value.empty:
        dow_jones_value = dow_jones_value.iat[0,0]

    #NASDAQ
    nasdaq_value = quandl.get("NASDAQOMX/COMP.1", authtoken="d-S-cB5976zhjoB4o-hm", start_date=input_date, end_date = input_end_date)
    if not nasdaq_value.empty:
        nasdaq_value = nasdaq_value.iat[0,0]
      
    #Crude Oil Prices: Brent - Europe
    crude_oil_value = quandl.get("FRED/DCOILBRENTEU", authtoken="d-S-cB5976zhjoB4o-hm", start_date=input_date, end_date = input_end_date)
    if not crude_oil_value.empty:
        crude_oil_value = crude_oil_value.iat[0,0]
        
    #USD VS EURO
    euro_value = quandl.get("CUR/EUR", authtoken="d-S-cB5976zhjoB4o-hm", start_date=input_date, end_date = input_end_date)
    if not euro_value.empty:
        euro_value = euro_value.iat[0,0]

        
    #USD VS YEN
    yen_value = quandl.get("CUR/JPY", authtoken="d-S-cB5976zhjoB4o-hm", start_date=input_date, end_date = input_end_date)
    if not yen_value.empty:
        yen_value = yen_value.iat[0,0]
                
    #USD TO GBP
    gbp_value = quandl.get("CUR/GBP", authtoken="d-S-cB5976zhjoB4o-hm", start_date=input_date, end_date = input_end_date)
    if not gbp_value.empty:
        gbp_value = gbp_value.iat[0,0]
        
    #USD TO INR 
    inr_value = quandl.get("CUR/INR", authtoken="d-S-cB5976zhjoB4o-hm", start_date=input_date, end_date = input_end_date)
    if not inr_value.empty:
        inr_value = inr_value.iat[0,0]
    
    #USD TO CANADIAN DOLLAR
    cad_value = quandl.get("CUR/CAD", authtoken="d-S-cB5976zhjoB4o-hm", start_date=input_date, end_date = input_end_date)
    if not cad_value.empty:
        cad_value = cad_value.iat[0,0]
        
    #LMBA GOLD PRICE
    gold_price_value = quandl.get("LBMA/GOLD.2", authtoken="d-S-cB5976zhjoB4o-hm", start_date=input_date, end_date = input_end_date)
    if not gold_price_value.empty:
        gold_price_value = gold_price_value.iat[0,0]
   
    i = i + 1
    print 'Date',str(input_date)
    print 'Inserting the record '+str(i)
    
    print bse_value,dow_jones_value,nasdaq_value,crude_oil_value,euro_value,yen_value,gbp_value,inr_value,cad_value,gold_price_value
    
    break

        
   # with open('GoldPricesFactors.csv','a') as f:
   #     writer = csv.writer(f)
   #     for row in writer:
   #         for i, in enumerate(row):
   #             if len(x)<1:
   #                 x = row[i] = 0
                    
   #     writer.writerow([input_date, bse_value.iat[0,0], dow_jones_value.iat[0,0], nasdaq_value.iat[0,0], 
   #                       crude_oil_value.iat[0,0], euro_value.iat[0,0], yen_value.iat[0,0], gbp_value.iat[0,0], 
   #                       inr_value.iat[0,0], cad_value.iat[0,0], gold_price_value.iat[0,0]])

print 'Completed'

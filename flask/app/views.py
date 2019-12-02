from app import app
import os
from pymongo import MongoClient
from flask import Flask, request
from bs4 import BeautifulSoup
import json
import requests
from bson.json_util import dumps

connection = MongoClient("mongodb://db:27017")

client = None
with open("./google_key.json","r") as clientJson :
    client = json.load(clientJson)
    
key=client["key"]

@app.route("/test")
def test():
    return "test successs"


@app.route("/getBcode", methods=['GET', 'POST'])
def getBcode():
    #place="서울 강남구 개포동"
    url = 'https://dapi.kakao.com/v2/local/search/address.json?query='+place
    headers = {"Authorization":"KakaoAK b8886236f62765e7833a53000718bb9a"}
    result = json.loads((requests.get(url,headers=headers).text))
    result_coord=(result.get("documents")[0]["address"]["b_code"])
    
    return result_coord

@app.route("/getPrice")
def getPrice(b_code):
    #b_code=request.args.get('code')
    #매매=A1
    #전세 =B1
    #월세 =B2
    #articleOrderCode=-3 :내림차순
    sell_max=""
    sell_min=""
    rent_max=""
    rent_min=""
    rent_m_max=""
    rent_m_min=""
    temp_code=1111011800
    ##매매 최고가
    #b_code=1168010300
    
    url = "https://land.naver.com/article/articleList.nhn?rletTypeCd=A01&tradeTypeCd=A1&hscpTypeCd=A01%3AA03%3AA04&cortarNo={}&articleOrderCode=-3&siteOrderCode=&cpId=kbstar&mapX=126.9657769&mapY=37.5753634&mapLevel=10&minPrc=&maxPrc=&minWrrnt=&maxWrrnt=&minLease=&maxLease=&minSpc=&maxSpc=&subDist=&mviDate=&hsehCnt=&rltrId=&mnex=&mHscpNo=&mPtpRange=&mnexOrder=&location=&ptpNo=&bssYm=&schlCd=&cmplYn=".format(b_code)
    html = requests.get(url).text
    soup = BeautifulSoup(html, 'html.parser')
    myList=soup.select('tbody')
    for list in myList:
        result = list.find('tr')
        result_2=result.find('td',{'class':'num align_r'})
        result_3=result_2.find('div',{'class':'inner'})
        result_4=result_3.find('strong').get('title')
        sell_max=result_4
    #매매 최저가 
    url = "https://land.naver.com/article/articleList.nhn?rletTypeCd=A01&tradeTypeCd=A1&hscpTypeCd=A01%3AA03%3AA04&cortarNo={}&articleOrderCode=3&siteOrderCode=&cpId=kbstar&mapX=126.9657769&mapY=37.5753634&mapLevel=10&minPrc=&maxPrc=&minWrrnt=&maxWrrnt=&minLease=&maxLease=&minSpc=&maxSpc=&subDist=&mviDate=&hsehCnt=&rltrId=&mnex=&mHscpNo=&mPtpRange=&mnexOrder=&location=&ptpNo=&bssYm=&schlCd=&cmplYn=".format(b_code)
    html = requests.get(url).text
    soup = BeautifulSoup(html, 'html.parser')
    myList=soup.select('tbody')
    for list in myList:
        result = list.find('tr')
        result_2=result.find('td',{'class':'num align_r'})
        result_3=result_2.find('div',{'class':'inner'})
        result_4=result_3.find('strong').get('title')
        sell_min=result_4
    #전세최고가
    url = "https://land.naver.com/article/articleList.nhn?rletTypeCd=A01&tradeTypeCd=B1&hscpTypeCd=A01%3AA03%3AA04&cortarNo={}&articleOrderCode=-3&siteOrderCode=&cpId=kbstar&mapX=126.9657769&mapY=37.5753634&mapLevel=10&minPrc=&maxPrc=&minWrrnt=&maxWrrnt=&minLease=&maxLease=&minSpc=&maxSpc=&subDist=&mviDate=&hsehCnt=&rltrId=&mnex=&mHscpNo=&mPtpRange=&mnexOrder=&location=&ptpNo=&bssYm=&schlCd=&cmplYn=".format(b_code)
    html = requests.get(url).text
    soup = BeautifulSoup(html, 'html.parser')
    myList=soup.select('tbody')
    for list in myList:
        result = list.find('tr')
        result_2=result.find('td',{'class':'num align_r'})
        result_3=result_2.find('div',{'class':'inner'})
        result_4=result_3.find('strong').get('title')
        rent_max=result_4
    #전세 최저가
    url = "https://land.naver.com/article/articleList.nhn?rletTypeCd=A01&tradeTypeCd=B1&hscpTypeCd=A01%3AA03%3AA04&cortarNo={}&articleOrderCode=3&siteOrderCode=&cpId=kbstar&mapX=126.9657769&mapY=37.5753634&mapLevel=10&minPrc=&maxPrc=&minWrrnt=&maxWrrnt=&minLease=&maxLease=&minSpc=&maxSpc=&subDist=&mviDate=&hsehCnt=&rltrId=&mnex=&mHscpNo=&mPtpRange=&mnexOrder=&location=&ptpNo=&bssYm=&schlCd=&cmplYn=".format(b_code)
    html = requests.get(url).text
    soup = BeautifulSoup(html, 'html.parser')
    myList=soup.select('tbody')
    for list in myList:
        result = list.find('tr')
        result_2=result.find('td',{'class':'num align_r'})
        result_3=result_2.find('div',{'class':'inner'})
        result_4=result_3.find('strong').get('title')
        rent_min=result_4
        
    #월세 최고가
    url = "https://land.naver.com/article/articleList.nhn?rletTypeCd=A01&tradeTypeCd=B2&hscpTypeCd=A01%3AA03%3AA04&cortarNo={}&articleOrderCode=-3&siteOrderCode=&cpId=kbstar&mapX=126.9657769&mapY=37.5753634&mapLevel=10&minPrc=&maxPrc=&minWrrnt=&maxWrrnt=&minLease=&maxLease=&minSpc=&maxSpc=&subDist=&mviDate=&hsehCnt=&rltrId=&mnex=&mHscpNo=&mPtpRange=&mnexOrder=&location=&ptpNo=&bssYm=&schlCd=&cmplYn=".format(b_code)
    html = requests.get(url).text
    soup = BeautifulSoup(html, 'html.parser')
    myList=soup.select('tbody')
    for list in myList:
        result = list.find('tr')
        result_2=result.find('td',{'class':'num align_r'})
        result_3=result_2.find('div',{'class':'inner'})
        result_4=result_3.find('strong').get('title')
        rent_m_max=result_4
    #월세 최저가
    url = "https://land.naver.com/article/articleList.nhn?rletTypeCd=A01&tradeTypeCd=B2&hscpTypeCd=A01%3AA03%3AA04&cortarNo={}&articleOrderCode=3&siteOrderCode=&cpId=kbstar&mapX=126.9657769&mapY=37.5753634&mapLevel=10&minPrc=&maxPrc=&minWrrnt=&maxWrrnt=&minLease=&maxLease=&minSpc=&maxSpc=&subDist=&mviDate=&hsehCnt=&rltrId=&mnex=&mHscpNo=&mPtpRange=&mnexOrder=&location=&ptpNo=&bssYm=&schlCd=&cmplYn=".format(b_code)
    html = requests.get(url).text
    soup = BeautifulSoup(html, 'html.parser')
    myList=soup.select('tbody')
    for list in myList:
        result = list.find('tr')
        result_2=result.find('td',{'class':'num align_r'})
        result_3=result_2.find('div',{'class':'inner'})
        result_4=result_3.find('strong').get('title')
        rent_m_min=result_4
    ans={'sell_max':sell_max, 'sell_min':sell_min, 'rent_max':rent_max, 'rent_min':rent_min, 'rent_m_max':rent_m_max, 'rent_m_min':rent_m_min}
    return ans
    
    
#place의 위도,경도값 반환
@app.route("/getCoord")
def getCoord(place):
    url = 'https://dapi.kakao.com/v2/local/search/address.json?query='+place
    headers = {"Authorization":"KakaoAK b8886236f62765e7833a53000718bb9a"}
    result = json.loads((requests.get(url,headers=headers).text))
    result_coord=(result.get("documents")[0]["x"], result.get("documents")[0]["y"])
    return (result_coord)

@app.route("/getCB")
def getCB(place):
    #place="서울 중구 서소문동"
    url = 'https://dapi.kakao.com/v2/local/search/address.json?query='+place
    headers = {"Authorization":"KakaoAK b8886236f62765e7833a53000718bb9a"}
    result = json.loads((requests.get(url,headers=headers).text))
    result_coord=(result.get("documents")[0]["x"], result.get("documents")[0]["y"])
    b_code=result.get("documents")[0]["address"]["b_code"]
    ans={"b_code":b_code,"coord":result_coord}
    json_ans=json.dumps(ans)
    return json_ans
    
@app.route("/getCoord_call", methods=['GET', 'POST'])
def getCoord_call():
    place = request.args.get('place')
    url = 'https://dapi.kakao.com/v2/local/search/address.json?query='+place
    headers = {"Authorization":"KakaoAK b8886236f62765e7833a53000718bb9a"}
    result = json.loads((requests.get(url,headers=headers).text))
    #result_coord=(result.get("documents")[0]["x"], result.get("documents")[0]["y"])
    result= json.dumps(result, ensure_ascii=False)
    #print(result)

    return result

#인풋 : 출발지, 목적지 위도경도,  아웃풋:출발지 에서 목적지 가는 경로(json)
@app.route("/getDriect")
def getDirect(source, dest):
    source_x=source[0]
    source_y=source[1]
    dest_x=dest[0]
    dest_y=dest[1]
    url = 'https://maps.googleapis.com/maps/api/directions/json?origin={},{}&destination={},{}&mode=transit&traffic_model=optimistic&departure_time=now&key={}'.format(source_y,source_x,dest_y,dest_x, key)
    #headers = {"Authorization":"KakaoAK b8886236f62765e7833a53000718bb9a"}
    result = json.loads((requests.get(url).text))
    #result= json.dumps(result, ensure_ascii=False)
    #print(result)
    return result

#통근 시간 반환.(이동하는 데 걸리는 시간, 테스트용으로 하드코딩함)
@app.route("/getRush")
def getRush(source, dest):
    #source='서울 중구 을지로2가'
    #dest='경기 가평군 설악면'
    source=getCoord(source)
    dest=getCoord(dest)
    json_direction=getDirect(source, dest)
    rush_hour=json_direction.get("routes")[0]["legs"][0]["duration"]["text"]
    rush_hour=rush_hour.split(' ')
    total=""
    if len(rush_hour) ==4:
        total=int(rush_hour[0])*60+int(rush_hour[2])
    else:
        total=int(rush_hour[0])
    return str(total)
    
    

@app.route("/search")
def search():
    #placeList=['경기도 수원시 영통구 광교동', '서울시 강남구 역삼동']

    arr=[]
    ans=[]
    #위도 경도 좌표 추출해서 arr에 추가
    for place in placeList:
        coord=getCoord(place)
        arr.append(coord)
        
    #arr에 저장된 좌표를 토대로 경로 확인.
    for coord in arr:
        json_direction=getDirect(coord)
        rush_hour=json_direction.get("routes")[0]["legs"][0]["duration"]["text"]
        print(rush_hour)
        rush_hour=rush_hour.split(' ')
        if len(rush_hour) ==4:
            total=int(rush_hour[0])*60+int(rush_hour[2])
        else:
            total=int(rush_hour[0])
        print("total= {}".format(total))
        ans.append(json_direction)
        ans.append("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
    return str(ans)

#디비에 넣는 함수인데, 넣으면 갑 바뀔 수 있으니 웬만하면 호출하지말것
@app.route("/insert_data")
def insert_data():
    r=open('/var/www/html/mediaProject/resource/incheon.txt', mode='r',encoding='utf-8', buffering=-1, errors=None, newline=None, closefd=True, opener=None)
    rc=open('/var/www/html/mediaProject/resource/total.txt', mode='r', encoding='utf-8', buffering=-1, errors=None, newline=None, closefd=True, opener=None)
    region_1='인천'
    region_2=None
    region_3=None
    c_region_1='서울'
    c_region_2=None
    c_region_3=None
    ans=[]
    #첫번째 반복문. 전체를 읽음.
    for line in r:
        str=line.rstrip('\n')
        if str == '서울' or str == '경기' or str == '인천':
            region_1 = str
            continue
        if str.count('[')==0:
            region_2=str
            continue
        
        #동 리스트들
        else:
            str = str.lstrip('[')
            str = str.rstrip(']')
            arr = str.split(', ')
            for place in arr:
            
                #initialize
                region_3=place.lstrip('\'')
                region_3=region_3.rstrip('\'')
                source=getCoord("{} {} {}".format(region_1,region_2, region_3))
                source_x=source[0]
                source_y=source[1]
                dist_10=[]
                dist_20=[]
                dist_30=[]
                dist_40=[]
                dist_50=[]
                
                #print("{} {} {}".format(region_1, region_2, region_3))
                for c_line in rc:
                    c_str=c_line.rstrip('\n')
                    if c_str == '서울' or c_str == '경기' or c_str == '인천':
                        c_region_1=c_str
                        continue
                    if c_str.count('[')==0:
                        c_region_2=c_str
                        continue
                    else:
                        c_str = c_str.lstrip('[')
                        c_str = c_str.rstrip(']')
                        c_arr = c_str.split(', ')
                        for c_place in c_arr:
                            #initialize
                            c_region_3=c_place.lstrip('\'')
                            c_region_3=c_region_3.rstrip('\'')
                            dest=getCoord("{} {} {}".format(c_region_1,c_region_2, c_region_3))
                            dest_x=dest[0]
                            dest_y=dest[1]
                            ans.append("{} {} {}".format(c_region_1,c_region_2,c_region_3))
                            
                            #경로탐색
                            json_direction=getDirect(source, dest)
                            rush_hour=json_direction.get("routes")[0]["legs"][0]["duration"]["text"]
                            print(rush_hour)
                            rush_hour=rush_hour.split(' ')
                            if len(rush_hour) ==4:
                                total=int(rush_hour[0])*60+int(rush_hour[2])
                            else:
                                total=int(rush_hour[0])
                            print("{} {} {} total= {}".format(c_region_1, c_region_2, c_region_3, total))
            rc.seek(0)
    print(ans)
    return "success"



#디비에 넣는 함수인데, 넣으면 갑 바뀔 수 있으니 웬만하면 호출하지말것
@app.route("/insert_sample")
def insert_sample():
    rc=open('/var/www/html/mediaProject/resource/total.txt', mode='r', encoding='utf-8', buffering=-1, errors=None, newline=None, closefd=True, opener=None)
    region_1='서울'
    region_2='중구'
    region_3='을지로2가'
    c_region_1='서울'
    c_region_2=None
    c_region_3=None
    source=getCoord("{} {} {}".format(region_1,region_2, region_3))
    
    s_dist_10=[]
    s_dist_20=[]
    s_dist_30=[]
    s_dist_40=[]
    s_dist_50=[]
    s_dist_60=[]
    s_dist_70=[]
    s_dist_80=[]
    s_dist_90=[]
    
    g_dist_10=[]
    g_dist_20=[]
    g_dist_30=[]
    g_dist_40=[]
    g_dist_50=[]
    g_dist_60=[]
    g_dist_70=[]
    g_dist_80=[]
    g_dist_90=[]
    
    i_dist_10=[]
    i_dist_20=[]
    i_dist_30=[]
    i_dist_40=[]
    i_dist_50=[]
    i_dist_60=[]
    i_dist_70=[]
    i_dist_80=[]
    i_dist_90=[]
    
    
    for c_line in rc:
        c_str=c_line.rstrip('\n')
        if c_str == '서울' or c_str == '경기' or c_str == '인천':
            c_region_1=c_str
            continue
        if c_str.count('[')==0:
            c_region_2=c_str
            continue
        
        else:
            c_str = c_str.lstrip('[')
            c_str = c_str.rstrip(']')
            c_arr = c_str.split(', ')
            for c_place in c_arr:
                #initialize
                c_region_3=c_place.lstrip('\'')
                c_region_3=c_region_3.rstrip('\'')
                dest=getCoord("{} {} {}".format(c_region_1,c_region_2, c_region_3))
                dest_x=dest[0]
                dest_y=dest[1]
                
                name="{} {} {}".format(c_region_1, c_region_2, c_region_3)
                #경로탐색
                #b_code=getBcode(name)
                #price=getPrice(b_code)
                
                json_direction=getDirect(source, dest)
                rush_hour=json_direction.get("routes")[0]["legs"][0]["duration"]["text"]
                rush_hour=rush_hour.split(' ')
                if len(rush_hour) ==4:
                    total=int(rush_hour[0])*60+int(rush_hour[2])
                else:
                    total=int(rush_hour[0])
                print("{} {} {} : {}분".format(c_region_1, c_region_2, c_region_3,str(total)))
                #{"name":name, "price":price}
                if total>=0 and total<=9:
                    if c_region_1 =='서울':
                        s_dist_10.append({"name":name})
                    elif c_region_1 =='경기':
                        g_dist_10.append({"name":name})
                    elif c_region_1 =='인천':
                        i_dist_10.append({"name":name})
                        
                elif total>=10 and total<=19:
                    if c_region_1 =='서울':
                        s_dist_20.append({"name":name})
                    elif c_region_1 =='경기':
                        g_dist_20.append({"name":name})
                    elif c_region_1 =='인천':
                        i_dist_20.append({"name":name})
                        
                elif total>=20 and total<=29:
                    if c_region_1 =='서울':
                        s_dist_30.append({"name":name})
                    elif c_region_1 =='경기':
                        g_dist_30.append({"name":name})
                    elif c_region_1 =='인천':
                        i_dist_30.append({"name":name})
                        
                elif total>=30 and total<=39:
                    if c_region_1 =='서울':
                        s_dist_40.append({"name":name})
                    elif c_region_1 =='경기':
                        g_dist_40.append({"name":name})
                    elif c_region_1 =='인천':
                        i_dist_40.append({"name":name})
                        
                elif total>=40 and total<=49:
                    if c_region_1 =='서울':
                        s_dist_50.append({"name":name})
                    elif c_region_1 =='경기':
                        g_dist_50.append({"name":name})
                    elif c_region_1 =='인천':
                        i_dist_50.append({"name":name})
                        
                elif total>=50 and total<=59:
                    if c_region_1 =='서울':
                        s_dist_60.append({"name":name})
                    elif c_region_1 =='경기':
                        g_dist_60.append({"name":name})
                    elif c_region_1 =='인천':
                        i_dist_60.append({"name":name})
                        
                elif total>=60 and total<=69:
                    if c_region_1 =='서울':
                        s_dist_70.append({"name":name})
                    elif c_region_1 =='경기':
                        g_dist_70.append({"name":name})
                    elif c_region_1 =='인천':
                        i_dist_70.append({"name":name})
                        
                elif total>=70 and total<=79:
                    if c_region_1 =='서울':
                        s_dist_80.append({"name":name})
                    elif c_region_1 =='경기':
                        g_dist_80.append({"name":name})
                    elif c_region_1 =='인천':
                        i_dist_80.append({"name":name})
                        
                elif total>=80 and total<=89:
                    if c_region_1 =='서울':
                        s_dist_90.append({"name":name})
                    elif c_region_1 =='경기':
                        g_dist_90.append({"name":name})
                    elif c_region_1 =='인천':
                        i_dist_90.append({"name":name})
                        
    s_place={
        "dist_10": s_dist_10,
        "dist_20": s_dist_20,
        "dist_30": s_dist_30,
        "dist_40": s_dist_40,
        "dist_50": s_dist_50,
        "dist_60": s_dist_60,
        "dist_70": s_dist_70,
        "dist_80": s_dist_80,
        "dist_90": s_dist_90
    }
    
    
    g_place={
        "dist_10": g_dist_10,
        "dist_20": g_dist_20,
        "dist_30": g_dist_30,
        "dist_40": g_dist_40,
        "dist_50": g_dist_50,
        "dist_60": g_dist_60,
        "dist_70": g_dist_70,
        "dist_80": g_dist_80,
        "dist_90": g_dist_90
    }
    
    i_place={
        "dist_10": i_dist_10,
        "dist_20": i_dist_20,
        "dist_30": i_dist_30,
        "dist_40": i_dist_40,
        "dist_50": i_dist_50,
        "dist_60": i_dist_60,
        "dist_70": i_dist_70,
        "dist_80": i_dist_80,
        "dist_90": i_dist_90
    }
    #place= json.dumps(place, ensure_ascii=False)
    #db에 insert
    
    connection = pymongo.MongoClient(host='127.0.0.1',port=27017)
    db=connection.geo_data
    db.seoul.update({"name": "중구"}, {"$push" : {"places":{"name":"을지로2가", "in_seoul":s_place, "in_gyeonggi":g_place, "in_incheon":i_place}}})
    
    return s_place


@app.route("/db_connect", methods=['GET'])
def db_connect():
    connection = pymongo.MongoClient(host='127.0.0.1',port=27017)
    db=connection.geo_data
    collection=db.get_collection('seoul')
    result=collection.find()
    result=dumps(result, ensure_ascii=False)
    print (result)
    return result


#키워드로 검색했을 때 주소,위도, 경도 등등 알려주는 함수.  
@app.route("/keyword")
def keyword():
    url='https://dapi.kakao.com/v2/local/search/keyword.json?query='+'대한민국 서울특별시 영등포구 영등포동3가'
    headers = {"Authorization":"KakaoAK b8886236f62765e7833a53000718bb9a"}
    result = json.loads((requests.get(url,headers=headers).text))
    #result= json.dumps(result, ensure_ascii=False)
    #print(result)
    print (type(result))
    print (result)
    return result


@app.route("/get_places")
def get_places():
    connection = pymongo.MongoClient(host='127.0.0.1',port=27017)
    db=connection.get_database('geo_data')
    collection=db.get_collection('seoul')
    #results=collection.find({'places':{'$elemMatch':{'name':"을지로2가"}}}, {'name':'true','places':{'in_seoul'}})
    results=dumps(collection.find({'places':{'$elemMatch':{'name':"을지로2가"}}}),ensure_ascii=False)
    edited_results=results[1:-1]
    json_object=json.loads(edited_results)
    json_places=json_object['places']
    ans=None
    place_10=None
    place_20=None
    place_30=None
    place_40=None
    place_50=None
    place_60=None
    place_70=None
    place_80=None
    place_90=None
    for data in json_places:
        placeList=data['in_gyeonggi']
        place_10=placeList['dist_10']
        place_20=placeList['dist_20']
        place_30=placeList['dist_30']
        place_40=placeList['dist_40']
        place_50=placeList['dist_50']
        place_60=placeList['dist_60']
        place_70=placeList['dist_70']
        place_80=placeList['dist_80']
        place_90=placeList['dist_90']
    ans={'dist_10':place_10,'dist_20':place_20,'dist_30':place_30,'dist_40':place_40,'dist_50':place_50,'dist_60':place_60,'dist_70':place_70,'dist_80':place_80,'dist_90':place_90}
        
        
    print(type(ans))
    print(ans)
    '''
    for result in results:
        print(result)
    '''
    #ans=json.loads(ans)
    return ans

@app.route("/get_detail", methods=['GET'])
def get_detail():
    source_address=request.args.get('source')
    dest_address=request.args.get('dest')
    #source_address="서울 중구 을지로2가"
    #dest_address="서울 동대문구 신설동"
    detail=getCB(source_address)
    rushHour=getRush(source_address,dest_address)
    json_detail=json.loads(detail)
    b_code=json_detail["b_code"]
    coord=json_detail["coord"]
    price=getPrice(b_code)
    ans={"coord":coord, "rushHour":rushHour, "price":price}
    json_ans=json.dumps(ans)
    #json_ans=json.loads(ans)
    
    return json_ans
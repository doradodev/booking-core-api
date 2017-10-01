CREATE TABLE ROOM (		ID_ROOM NUMBER auto_increment,
						NAME VARCHAR(50),
						CAPACITY NUMBER, 
						PRIMARY KEY(ID_ROOM));
						
CREATE TABLE BOOKING (  ID_BOOKING NUMBER auto_increment,
                        ID_ROOM NUMBER, 
                        START_DATE TIMESTAMP,
                        END_DATE TIMESTAMP,
                        STATE VARCHAR(50),
                        PRIMARY KEY(ID_BOOKING));

                        ALTER TABLE BOOKING 
                        ADD CONSTRAINT FK_ID_ROOM
                        FOREIGN KEY (ID_ROOM) 
                        REFERENCES ROOM(ID_ROOM);


INSERT INTO ROOM   (NAME,CAPACITY) VALUES(
		'Principal Meeting Room',
		30		
	);
INSERT INTO ROOM   (NAME,CAPACITY) VALUES(
		'Secondary Meeting Room',
		20		
	);	


INSERT INTO BOOKING (ID_ROOM, START_DATE,END_DATE,STATE) VALUES(	
	1,
	'2017-09-30T16:00:00.001Z',
	'2017-09-30T17:00:00.001Z',
    'SOLICITADA'
);
INSERT INTO BOOKING (ID_ROOM, START_DATE,END_DATE, STATE) VALUES(	
	1,
	'2017-09-30T17:00:00.001Z',
	'2017-09-30T18:00:00.001Z',
    'SOLICITADA'
);

	
	

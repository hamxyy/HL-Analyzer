


/$current side; binaural in scope of PostTargetProcessing()-macro
RightSide           only right
LeftSide            only left
BothSides           right and left
NoSide              no side$/
const env enum Sides 22000
{
    RightSide = 0, // only right
    LeftSide = 1, // only left
    BothSides = 2, // right and left
    NoSide = 3 // no side
};

/$current program$/
const env int Prog 22001
{0,5};

/$the manufacturer of the hearing instrument
Siemens             SAT GmbH, Erlangen
Rexton              Rexton, Inc, Plymouth, Minnesota, US
Hansaton            Hansaton Akustische Geräte GmbH, Salzburg, Austria
Audioservice        Audioservice
Lavis               LAVIS
AandM               A&M Hearing Limited, UK
Electone            Electone
Symphonix           Symphonix Devices, Inc, San Jose, CA, US
MiracleEar          Miracle Ear, US
Rion                RION
Costco              Costco 
Audionova           new manufacturer for Netherland (Jean Louis Joly) FR37989
Test                only for test purpodse - can be used as next free company
Invalid             programmed manufacturer code is not valid
Unknown             no manufacturer code programmed yet
BrandNeutral        reserved value for brand neutral accessory handling$/
const env enum CompanyCode 22004
{
    Siemens = 0, // SAT GmbH, Erlangen
    Rexton = 1, // Rexton, Inc, Plymouth, Minnesota, US
    Hansaton = 2, // Hansaton Akustische Geräte GmbH, Salzburg, Austria
    Audioservice = 3, // Audioservice
    Lavis = 4, // LAVIS
    AandM = 5, // A&M Hearing Limited, UK
    Electone = 6, // Electone
    Symphonix = 7, // Symphonix Devices, Inc, San Jose, CA, US
    MiracleEar = 8, // Miracle Ear, US
    Rion = 9, // RION
    Costco = 10, // Costco 
    Audionova = 13, // new manufacturer for Netherland (Jean Louis Joly) FR37989
    Test = 14, // only for test purpodse - can be used as next free company
    Invalid = 15, // programmed manufacturer code is not valid
    Unknown = 16, // no manufacturer code programmed yet
    BrandNeutral = 32 // reserved value for brand neutral accessory handling
};

/$ISO3361 CountryCode
AF                  Afghanistan
EG                  Aegypten
AX                  Aland
AL                  Albanien
DZ                  Algerien
AS                  Amerikanisch-Samoa
VI                  Amerikanische Jungferninseln
AD                  Andorra
AO                  Angola
AI                  Anguilla
AQ                  Antarktika
AG                  Antigua und Barbuda
GQ                  Aequatorialguinea
AR                  Argentinien
AM                  Armenien
AW                  Aruba
AZ                  Aserbaidschan
ET                  Athiopien
AU                  Australien
BS                  Bahamas
BH                  Bahrain
BD                  Bangladesch
BB                  Barbados
BY                  Belarus (Weissrussland)
BE                  Belgien
BZ                  Belize
BJ                  Benin
BM                  Bermuda
BT                  Bhutan
BO                  Bolivien
BQ                  Bonaire, Sint Eustatius
BA                  Bosnien und Herzegowina
BW                  Botswana
BV                  Bouvetinsel
BR                  Brasilien
VG                  Britische Jungferninseln
IO                  Britisches Territorium im Indischen Ozean
BN                  Brunei Darussalam
BG                  Bulgarien
BF                  Burkina Faso
BI                  Burundi
CL                  Chile
CN                  China, Volksrepublik
CK                  Cookinseln
CR                  Costa Rica
CI                  Cote d'Ivoire(Elfenbeinkueste)
CW                  Curacao
DK                  Daenemark
DE                  Deutschland
DM                  Dominica
DO                  Dominikanische Republik
DJ                  Dschibuti
EC                  Ecuador
SV                  El Salvador
ER                  Eritrea
EE                  Estland
FK                  Falklandinseln
FO                  Faroer
FJ                  Fidschi
FI                  Finnland
FR                  Frankreich
GF                  Franzoesisch-Guayana
PF                  Franzoesisch-Polynesien
TF                  Franzoesische Antarktisgebiete
GA                  Gabun
GM                  Gambia
GE                  Georgien
GH                  Ghana
GI                  Gibraltar
GD                  Grenada
GR                  Griechenland
GL                  Groenland
GP                  Guadeloupe
GU                  Guam
GT                  Guatemala
GG                  Guernsey (Kanalinsel)
GN                  Guinea
GW                  Guinea-Bissau
GY                  Guyana
HT                  Haiti
HM                  Heard und McDonaldinseln
HN                  Honduras
HK                  Hongkong
IN                  Indien
ID                  Indonesien
IM                  Insel Man
IQ                  Irak
IR                  Iran, Islamische Republik
IE                  Irland
IS                  Island
IL                  Israel
IT                  Italien
JM                  Jamaika
JP                  Japan
YE                  Jemen
JE                  Jersey (Kanalinsel)
JO                  Jordanien
KY                  Kaimaninseln
KH                  Kambodscha
CM                  Kamerun
CA                  Kanada
CV                  Kap Verde
KZ                  Kasachstan
QA                  Katar
KE                  Kenia
KG                  Kirgisistan
KI                  Kiribati
CC                  Kokosinseln
CO                  Kolumbien
KM                  Komoren
CD                  Kongo, Demokratische Republik
CG                  Republik Kongo
KP                  Korea, Demokratische Volksrepublik (Nordkorea)
KR                  Korea, Republik (Suedkorea)
HR                  Kroatien
CU                  Kuba
KW                  Kuwait
LA                  Laos, Demokratische Volksrepublik
LS                  Lesotho
LV                  Lettland
LB                  Libanon
LR                  Liberia
LY                  Libysch-Arabische Dschamahirija (Libyen)
LI                  Liechtenstein
LT                  Litauen
LU                  Luxemburg
MO                  Macao
MG                  Madagaskar
MW                  Malawi
MY                  Malaysia
MV                  Malediven
ML                  Mali
MT                  Malta
MA                  Marokko
MH                  Marshallinseln
MQ                  Martinique
MR                  Mauretanien
MU                  Mauritius
YT                  Mayotte
MK                  Mazedonien
MX                  Mexiko
FM                  Mikronesien
MD                  Moldawien (Republik Moldau)
MC                  Monaco
MN                  Mongolei
ME                  Montenegro
MS                  Montserrat
MZ                  Mosambik
MM                  Myanmar (Burma)
NA                  Namibia
NR                  Nauru
NP                  Nepal
NC                  Neukaledonien
NZ                  Neuseeland
NI                  Nicaragua
NL                  Niederlande
AN                  Niederlaendische Antillen
NE                  Niger
NG                  Nigeria
NU                  Niue
MP                  Noerdliche Marianen
NF                  Norfolkinsel
NO                  Norwegen
OM                  Oman
AT                  Oesterreich
PK                  Pakistan
PS                  Palaestinensische Autonomiegebiete
PW                  Palau
PA                  Panama
PG                  Papua-Neuguinea
PY                  Paraguay
PE                  Peru
PH                  Philippinen
PN                  Pitcairninseln
PL                  Polen
PT                  Portugal
PR                  Puerto Rico
RE                  Reunion
RW                  Ruanda
RO                  Rumaenien
RU                  Russische Foederation
SB                  Salomonen
BL                  Saint-Barthelemy
MF                  Saint-Martin (franz. Teil)
ZM                  Sambia
WS                  Samoa
SM                  San Marino
ST                  Sao Tome und Principe
SA                  Saudi-Arabien
SE                  Schweden
CH                  Schweiz (Confoederatio Helvetica)
SN                  Senegal
RS                  Serbien
SC                  Seychellen
SL                  Sierra Leone
ZW                  Simbabwe
SG                  Singapur
SX                  Sint Maarten (niederl. Teil)
SK                  Slowakei
SI                  Slowenien
SO                  Somalia
ES                  Spanien
LK                  Sri Lanka
SH                  St. Helena
KN                  St. Kitts und Nevis
LC                  St. Lucia
PM                  St. Pierre und Miquelon
VC                  St. Vincent und die Grenadinen
ZA                  Sudafrika
SD                  Sudan
GS                  Sudgeorgien und die Suedlichen Sandwichinseln
SS                  Sudsudan
SR                  Surialpha
SJ                  Svalbard und Jan Mayen
SZ                  Swasiland
SY                  Syrien, Arabische Republik
TJ                  Tadschikistan
TW                  Republik China (Taiwan)
TZ                  Tansania, Vereinigte Republik
TH                  Thailand
TG                  Togo
TK                  Tokelau
TO                  Tonga
TT                  Trinidad und Tobago
TD                  Tschad
CZ                  Tschechische Republik
TN                  Tunesien
TR                  Turkei
TM                  Turkmenistan
TC                  Turks- und Caicosinseln
TV                  Tuvalu
UG                  Uganda
UA                  Ukraine
HU                  Ungarn
UM                  United States Minor Outlying Islands
UY                  Uruguay
UZ                  Usbekistan
VU                  Vanuatu
VA                  Vatikanstadt
VE                  Venezuela
AE                  Vereinigte Arabische Emirate
US                  Vereinigte Staaten von Amerika
GB                  Vereinigtes Koenigreich Grossbritannien u.Nordirland
VN                  Vietnam
WF                  Wallis und Futuna
CX                  Weihnachtsinsel
EH                  Westsahara
CF                  Zentralafrikanische Republik
CY                  Zypern$/
const env enum Country 22005
{
    AF = 4, // Afghanistan
    EG = 818, // Aegypten
    AX = 248, // Aland
    AL = 8, // Albanien
    DZ = 12, // Algerien
    AS = 16, // Amerikanisch-Samoa
    VI = 850, // Amerikanische Jungferninseln
    AD = 20, // Andorra
    AO = 24, // Angola
    AI = 660, // Anguilla
    AQ = 10, // Antarktika
    AG = 28, // Antigua und Barbuda
    GQ = 226, // Aequatorialguinea
    AR = 32, // Argentinien
    AM = 51, // Armenien
    AW = 533, // Aruba
    AZ = 31, // Aserbaidschan
    ET = 231, // Athiopien
    AU = 36, // Australien
    BS = 44, // Bahamas
    BH = 48, // Bahrain
    BD = 50, // Bangladesch
    BB = 52, // Barbados
    BY = 112, // Belarus (Weissrussland)
    BE = 56, // Belgien
    BZ = 84, // Belize
    BJ = 204, // Benin
    BM = 60, // Bermuda
    BT = 64, // Bhutan
    BO = 68, // Bolivien
    BQ = 535, // Bonaire, Sint Eustatius
    BA = 70, // Bosnien und Herzegowina
    BW = 72, // Botswana
    BV = 74, // Bouvetinsel
    BR = 76, // Brasilien
    VG = 92, // Britische Jungferninseln
    IO = 86, // Britisches Territorium im Indischen Ozean
    BN = 96, // Brunei Darussalam
    BG = 100, // Bulgarien
    BF = 854, // Burkina Faso
    BI = 108, // Burundi
    CL = 152, // Chile
    CN = 156, // China, Volksrepublik
    CK = 184, // Cookinseln
    CR = 188, // Costa Rica
    CI = 384, // Cote d'Ivoire(Elfenbeinkueste)
    CW = 531, // Curacao
    DK = 208, // Daenemark
    DE = 276, // Deutschland
    DM = 212, // Dominica
    DO = 214, // Dominikanische Republik
    DJ = 262, // Dschibuti
    EC = 218, // Ecuador
    SV = 222, // El Salvador
    ER = 232, // Eritrea
    EE = 233, // Estland
    FK = 238, // Falklandinseln
    FO = 234, // Faroer
    FJ = 242, // Fidschi
    FI = 246, // Finnland
    FR = 250, // Frankreich
    GF = 254, // Franzoesisch-Guayana
    PF = 258, // Franzoesisch-Polynesien
    TF = 260, // Franzoesische Antarktisgebiete
    GA = 266, // Gabun
    GM = 270, // Gambia
    GE = 268, // Georgien
    GH = 288, // Ghana
    GI = 292, // Gibraltar
    GD = 308, // Grenada
    GR = 300, // Griechenland
    GL = 304, // Groenland
    GP = 312, // Guadeloupe
    GU = 316, // Guam
    GT = 320, // Guatemala
    GG = 831, // Guernsey (Kanalinsel)
    GN = 324, // Guinea
    GW = 624, // Guinea-Bissau
    GY = 328, // Guyana
    HT = 332, // Haiti
    HM = 334, // Heard und McDonaldinseln
    HN = 340, // Honduras
    HK = 344, // Hongkong
    IN = 356, // Indien
    ID = 360, // Indonesien
    IM = 833, // Insel Man
    IQ = 368, // Irak
    IR = 364, // Iran, Islamische Republik
    IE = 372, // Irland
    IS = 352, // Island
    IL = 376, // Israel
    IT = 380, // Italien
    JM = 388, // Jamaika
    JP = 392, // Japan
    YE = 887, // Jemen
    JE = 832, // Jersey (Kanalinsel)
    JO = 400, // Jordanien
    KY = 136, // Kaimaninseln
    KH = 116, // Kambodscha
    CM = 120, // Kamerun
    CA = 124, // Kanada
    CV = 132, // Kap Verde
    KZ = 398, // Kasachstan
    QA = 634, // Katar
    KE = 404, // Kenia
    KG = 417, // Kirgisistan
    KI = 296, // Kiribati
    CC = 166, // Kokosinseln
    CO = 170, // Kolumbien
    KM = 174, // Komoren
    CD = 180, // Kongo, Demokratische Republik
    CG = 178, // Republik Kongo
    KP = 408, // Korea, Demokratische Volksrepublik (Nordkorea)
    KR = 410, // Korea, Republik (Suedkorea)
    HR = 191, // Kroatien
    CU = 192, // Kuba
    KW = 414, // Kuwait
    LA = 418, // Laos, Demokratische Volksrepublik
    LS = 426, // Lesotho
    LV = 428, // Lettland
    LB = 422, // Libanon
    LR = 430, // Liberia
    LY = 434, // Libysch-Arabische Dschamahirija (Libyen)
    LI = 438, // Liechtenstein
    LT = 440, // Litauen
    LU = 442, // Luxemburg
    MO = 446, // Macao
    MG = 450, // Madagaskar
    MW = 454, // Malawi
    MY = 458, // Malaysia
    MV = 462, // Malediven
    ML = 466, // Mali
    MT = 470, // Malta
    MA = 504, // Marokko
    MH = 584, // Marshallinseln
    MQ = 474, // Martinique
    MR = 478, // Mauretanien
    MU = 480, // Mauritius
    YT = 175, // Mayotte
    MK = 807, // Mazedonien
    MX = 484, // Mexiko
    FM = 583, // Mikronesien
    MD = 498, // Moldawien (Republik Moldau)
    MC = 492, // Monaco
    MN = 496, // Mongolei
    ME = 499, // Montenegro
    MS = 500, // Montserrat
    MZ = 508, // Mosambik
    MM = 104, // Myanmar (Burma)
    NA = 516, // Namibia
    NR = 520, // Nauru
    NP = 524, // Nepal
    NC = 540, // Neukaledonien
    NZ = 554, // Neuseeland
    NI = 558, // Nicaragua
    NL = 528, // Niederlande
    AN = 530, // Niederlaendische Antillen
    NE = 562, // Niger
    NG = 566, // Nigeria
    NU = 570, // Niue
    MP = 580, // Noerdliche Marianen
    NF = 574, // Norfolkinsel
    NO = 578, // Norwegen
    OM = 512, // Oman
    AT = 40, // Oesterreich
    PK = 586, // Pakistan
    PS = 275, // Palaestinensische Autonomiegebiete
    PW = 585, // Palau
    PA = 591, // Panama
    PG = 598, // Papua-Neuguinea
    PY = 600, // Paraguay
    PE = 604, // Peru
    PH = 608, // Philippinen
    PN = 612, // Pitcairninseln
    PL = 616, // Polen
    PT = 620, // Portugal
    PR = 630, // Puerto Rico
    RE = 638, // Reunion
    RW = 646, // Ruanda
    RO = 642, // Rumaenien
    RU = 643, // Russische Foederation
    SB = 90, // Salomonen
    BL = 652, // Saint-Barthelemy
    MF = 663, // Saint-Martin (franz. Teil)
    ZM = 894, // Sambia
    WS = 882, // Samoa
    SM = 674, // San Marino
    ST = 678, // Sao Tome und Principe
    SA = 682, // Saudi-Arabien
    SE = 752, // Schweden
    CH = 756, // Schweiz (Confoederatio Helvetica)
    SN = 686, // Senegal
    RS = 688, // Serbien
    SC = 690, // Seychellen
    SL = 694, // Sierra Leone
    ZW = 716, // Simbabwe
    SG = 702, // Singapur
    SX = 534, // Sint Maarten (niederl. Teil)
    SK = 703, // Slowakei
    SI = 705, // Slowenien
    SO = 706, // Somalia
    ES = 724, // Spanien
    LK = 144, // Sri Lanka
    SH = 654, // St. Helena
    KN = 659, // St. Kitts und Nevis
    LC = 662, // St. Lucia
    PM = 666, // St. Pierre und Miquelon
    VC = 670, // St. Vincent und die Grenadinen
    ZA = 710, // Sudafrika
    SD = 729, // Sudan
    GS = 239, // Sudgeorgien und die Suedlichen Sandwichinseln
    SS = 728, // Sudsudan
    SR = 740, // Surialpha
    SJ = 744, // Svalbard und Jan Mayen
    SZ = 748, // Swasiland
    SY = 760, // Syrien, Arabische Republik
    TJ = 762, // Tadschikistan
    TW = 158, // Republik China (Taiwan)
    TZ = 834, // Tansania, Vereinigte Republik
    TH = 764, // Thailand
    TG = 768, // Togo
    TK = 772, // Tokelau
    TO = 776, // Tonga
    TT = 780, // Trinidad und Tobago
    TD = 148, // Tschad
    CZ = 203, // Tschechische Republik
    TN = 788, // Tunesien
    TR = 792, // Turkei
    TM = 795, // Turkmenistan
    TC = 796, // Turks- und Caicosinseln
    TV = 798, // Tuvalu
    UG = 800, // Uganda
    UA = 804, // Ukraine
    HU = 348, // Ungarn
    UM = 581, // United States Minor Outlying Islands
    UY = 858, // Uruguay
    UZ = 860, // Usbekistan
    VU = 548, // Vanuatu
    VA = 336, // Vatikanstadt
    VE = 862, // Venezuela
    AE = 784, // Vereinigte Arabische Emirate
    US = 840, // Vereinigte Staaten von Amerika
    GB = 826, // Vereinigtes Koenigreich Grossbritannien u.Nordirland
    VN = 704, // Vietnam
    WF = 876, // Wallis und Futuna
    CX = 162, // Weihnachtsinsel
    EH = 732, // Westsahara
    CF = 140, // Zentralafrikanische Republik
    CY = 196 // Zypern
};

/$mode of the acting hearing instrument driver
Fitting             the only allowed mode for the fitting application
Service             standard work mode for the service application (a.k.a. Hicoss)
ServiceAudiologicalResearchincludes eTemplate import and the research UI is used
ServiceSoftwareResearchno eTemplate import, combined and high-level controls are in use, research UI is used
ServiceHardwareResearchno eTemplate import, full access to all low-level controls, research UI is used$/
const env enum HiDriverWorkMode 22006
{
    Fitting = 0, // the only allowed mode for the fitting application
    Service = 1, // standard work mode for the service application (a.k.a. Hicoss)
    ServiceAudiologicalResearch = 2, // includes eTemplate import and the research UI is used
    ServiceSoftwareResearch = 3, // no eTemplate import, combined and high-level controls are in use, research UI is used
    ServiceHardwareResearch = 4 // no eTemplate import, full access to all low-level controls, research UI is used
};

/$the currently detected or simulated hearing instruments
RightSide           only right
LeftSide            only left
BothSides           right and left
NoSide              no side$/
const env enum DetHi 22007
{
    RightSide = 0, // only right
    LeftSide = 1, // only left
    BothSides = 2, // right and left
    NoSide = 3 // no side
};

/$number of available programs$/
const env int ProgramSlots 22008
{1,6};

/$current side, used for HI
Right               right side
Left                left side$/
const env enum OneSide 22009
{
    Right = 0, // right side
    Left = 1 // left side
};

/$indicating, whether devices can be used together for binaural signal processing
NotUsableTogether   not usable together
UsableTogether      usable together$/
const env enum BinauralDirMicCompatibleMicType 22010
{
    NotUsableTogether = 0, // not usable together
    UsableTogether = 1 // usable together
};

/$indicating, whether less demanding algorithms (like CROS) are working
NotUsableTogether   not usable together
UsableTogether      usable together$/
const env enum BinauralCompatibleMicType 22011
{
    NotUsableTogether = 0, // not usable together
    UsableTogether = 1 // usable together
};

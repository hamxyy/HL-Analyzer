


/$Kategorie des HI$/
const cap enum HICategory 20001
{
    BTE = 0,
    LIFE = 1,
    RIC = 2,
    ITE = 3,
    Module = 4,
    Implant = 5
};

/$Shelltype$/
const cap enum IteShell 20002
{
    nonITE = 0,
    ITE = 1,
    HS = 2,
    ITC = 3,
    MC = 4,
    CIC = 5
};

/$HI with local VC (HW availability)
noVC                no VC
endlessVC           endless VC (standard)
endstopVC           endstop VC (SP-devices)
SwUcConfigurableVC  VC by SW-UC-Configurable (RockerSwitch)$/
const cap enum VcLocal 20003
{
    noVC = 0, // no VC
    endlessVC = 1, // endless VC (standard)
    endstopVC = 2, // endstop VC (SP-devices)
    SwUcConfigurableVC = 3 // VC by SW-UC-Configurable (RockerSwitch)
};

/$HI with remote VC
noVC                no remote VC
remoteVC            VC remote via e2e and/or RCU$/
const cap enum VcRemote 20004
{
    noVC = 0, // no remote VC
    remoteVC = 1 // VC remote via e2e and/or RCU
};

/$Program Control
none                no Program Control
exists              Program Control available
SwUcConfigurable    Pb by SW-UC-Configurable (RockerSwitch)$/
const cap enum ProgramControlLocal 20005
{
    none = 0, // no Program Control
    exists = 1, // Program Control available
    SwUcConfigurable = 2 // Pb by SW-UC-Configurable (RockerSwitch)
};

/$HI with remote Program change
none                no remote Program Control
exists              Program Control remote via e2e and/or RCU$/
const cap enum ProgramControlRemote 20006
{
    none = 0, // no remote Program Control
    exists = 1 // Program Control remote via e2e and/or RCU
};

/$determines, which kind of remote user control is available
none                no wireless connections available
e2e                 e2e available only
RCU                 RCU available only
e2eRCU              e2e and RCU available$/
const cap enum WlsUserControl 20007
{
    none = 0, // no wireless connections available
    e2e = 1, // e2e available only
    RCU = 2, // RCU available only
    e2eRCU = 3 // e2e and RCU available
};

/$Bluetooth Audio available
none                Bluetooth Audio not available
exists              Bluetooth Audio available$/
const cap enum WlsAudio 20008
{
    none = 0, // Bluetooth Audio not available
    exists = 1 // Bluetooth Audio available
};

/$Bluetooth Phone available
none                Bluetooth Phone not available
exists              Bluetooth Phone available$/
const cap enum WlsPhone 20009
{
    none = 0, // Bluetooth Phone not available
    exists = 1 // Bluetooth Phone available
};

/$Support of Wireless Programming
no                  Wireless Programming not supported
yes                 Wireless Programming supported$/
const cap enum WlsProgramming 20010
{
    no = 0, // Wireless Programming not supported
    yes = 1 // Wireless Programming supported
};

/$audioshoe available
none                audioshoe not available
exists              audioshoe available$/
const cap enum AudioshoeAvailable 20011
{
    none = 0, // audioshoe not available
    exists = 1 // audioshoe available
};

/$Telecoil available
none                no TC
exists              TC available$/
const cap enum TelecoilAvailable 20012
{
    none = 0, // no TC
    exists = 1 // TC available
};

/$Switchless Telecoil available
none                Switchless Tcoil not available
exists              Stcoil available$/
const cap enum AutocoilAvailable 20013
{
    none = 0, // Switchless Tcoil not available
    exists = 1 // Stcoil available
};

/$Direktional oder omnidirektional
omni                omnidirectional
dir                 directional$/
const cap enum DirMicAvailable 20014
{
    omni = 0, // omnidirectional
    dir = 1 // directional
};

/$AOR available
none                no AOR
exists              AOR$/
const cap enum AorAvailable 20015
{
    none = 0, // no AOR
    exists = 1 // AOR
};

/$Datalearning and logging
none                none
DataLogging         DataLogging
DataLearning        DataLearning
SoundLearning       SoundLearning
CBL                 Classificator based learning$/
const cap enum TrainableType 20016
{
    none = 0, // none
    DataLogging = 1, // DataLogging
    DataLearning = 2, // DataLearning
    SoundLearning = 3, // SoundLearning
    CBL = 4 // Classificator based learning
};

/$Noise Reduction Learning and logging (D8 and later)
none                none
Logging             Logging
Learning            Learning$/
const cap enum TrainableNrType 20017
{
    none = 0, // none
    Logging = 1, // Logging
    Learning = 2 // Learning
};

/$Noiser available
none                Tinnitus masker not available
MaskerAvailable     Tinnitus masker available
NoiserOnly          HI is Noiser only$/
const cap enum TinMaskerAvailable 20018
{
    none = 0, // Tinnitus masker not available
    MaskerAvailable = 1, // Tinnitus masker available
    NoiserOnly = 2 // HI is Noiser only
};

/$Number of minimum available programs$/
const cap int NoOfAvailableProgramsMin 20019
{1,6};

/$Number of maximum available programs$/
const cap int NoOfAvailableProgramsMax 20020
{1,6};

/$Number of programs in delivery$/
const cap int ProgNumDelivery 20021
{1,6};

/$Availability of Super Power acclimatization steps
none                No Super Power acclimatization steps available
SPF                 Super Power Fit
SPLF                Super Power Linear Fit
SPFandSPLF          Super Power Fit and Super Power Linear Fit$/
const cap enum SuperPowerAcc 20022
{
    none = 0, // No Super Power acclimatization steps available
    SPF = 1, // Super Power Fit
    SPLF = 2, // Super Power Linear Fit
    SPFandSPLF = 3 // Super Power Fit and Super Power Linear Fit
};

/$Value used in asstnt.ini for strategy customization e.g. [13_3_NALNL1_Acc02] uses acc type value 3$/
const cap int AccType 20023
{1,255};

/$Default Acc-Level$/
const cap int DefAccLevel 20024
{1,6};

/$Hi Identifier$/
const cap int HiIdentifier 20025
{0,65535};

/$OverrulingGroup1$/
const cap int OverrulingGroup1 20026
{0,65535};

/$PricePoint
PP75                PricePoint 75
PP100               PricePoint 100
PP300               PricePoint 300
PP500               PricePoint 500
PP700               PricePoint 700$/
const cap enum PricePoint 20027
{
    PP75 = 75, // PricePoint 75
    PP100 = 100, // PricePoint 100
    PP300 = 300, // PricePoint 300
    PP500 = 500, // PricePoint 500
    PP700 = 700 // PricePoint 700
};

/$GMax$/
const cap int GMax 20028
{0,65535};

/$LOMax$/
const cap int LoMax 20029
{0,65535};

/$Running number$/
const cap int RunningNo 20030
{0,4095};

/$Family number$/
const cap int FamilyNo 20031
{0,255};

/$Receiver type$/
const cap enum ReceiverType 20032
{
    RIC_UNKNOWN = 0,
    RIC_NONE = 1,
    RIC_S = 2,
    RIC_M = 3,
    RIC_P = 4,
    RIC_HP = 5
};

/$Listening test prog 1$/
const cap enum ListeningTestForHP1 20033
{
    ListeningTestFog = 0,
    ListeningTestWirelessAudio = 1,
    ListeningTestTelecoil = 2,
    ListeningTestAudioshoe = 3,
    ListeningTestMaxMpoAtt = 4,
    ListeningTestFogMinus7 = 5,
    ListeningTestFogMinus20 = 6,
    ListeningTestMinGainBelow1kHz = 7,
    ListeningTestNoiser = 8,
    ListeningTestFogDirStatic = 9,
    ListeningTestMaxMpoAttDirStatic = 10,
    ListeningTestFogMinus7DirStatic = 11,
    ListeningTestFogMinus20DirStatic = 12,
    ListeningTestMinGainBelow1kHzDirStatic = 13
};

/$Listening test prog 2$/
const cap enum ListeningTestForHP2 20034
{
    ListeningTestFog = 0,
    ListeningTestWirelessAudio = 1,
    ListeningTestTelecoil = 2,
    ListeningTestAudioshoe = 3,
    ListeningTestMaxMpoAtt = 4,
    ListeningTestFogMinus7 = 5,
    ListeningTestFogMinus20 = 6,
    ListeningTestMinGainBelow1kHz = 7,
    ListeningTestNoiser = 8,
    ListeningTestFogDirStatic = 9,
    ListeningTestMaxMpoAttDirStatic = 10,
    ListeningTestFogMinus7DirStatic = 11,
    ListeningTestFogMinus20DirStatic = 12,
    ListeningTestMinGainBelow1kHzDirStatic = 13
};

/$Listening test prog 3$/
const cap enum ListeningTestForHP3 20035
{
    ListeningTestFog = 0,
    ListeningTestWirelessAudio = 1,
    ListeningTestTelecoil = 2,
    ListeningTestAudioshoe = 3,
    ListeningTestMaxMpoAtt = 4,
    ListeningTestFogMinus7 = 5,
    ListeningTestFogMinus20 = 6,
    ListeningTestMinGainBelow1kHz = 7,
    ListeningTestNoiser = 8,
    ListeningTestFogDirStatic = 9,
    ListeningTestMaxMpoAttDirStatic = 10,
    ListeningTestFogMinus7DirStatic = 11,
    ListeningTestFogMinus20DirStatic = 12,
    ListeningTestMinGainBelow1kHzDirStatic = 13
};

/$Listening test prog 4$/
const cap enum ListeningTestForHP4 20036
{
    ListeningTestFog = 0,
    ListeningTestWirelessAudio = 1,
    ListeningTestTelecoil = 2,
    ListeningTestAudioshoe = 3,
    ListeningTestMaxMpoAtt = 4,
    ListeningTestFogMinus7 = 5,
    ListeningTestFogMinus20 = 6,
    ListeningTestMinGainBelow1kHz = 7,
    ListeningTestNoiser = 8,
    ListeningTestFogDirStatic = 9,
    ListeningTestMaxMpoAttDirStatic = 10,
    ListeningTestFogMinus7DirStatic = 11,
    ListeningTestFogMinus20DirStatic = 12,
    ListeningTestMinGainBelow1kHzDirStatic = 13
};

/$Listening test prog 5$/
const cap enum ListeningTestForHP5 20037
{
    ListeningTestFog = 0,
    ListeningTestWirelessAudio = 1,
    ListeningTestTelecoil = 2,
    ListeningTestAudioshoe = 3,
    ListeningTestMaxMpoAtt = 4,
    ListeningTestFogMinus7 = 5,
    ListeningTestFogMinus20 = 6,
    ListeningTestMinGainBelow1kHz = 7,
    ListeningTestNoiser = 8,
    ListeningTestFogDirStatic = 9,
    ListeningTestMaxMpoAttDirStatic = 10,
    ListeningTestFogMinus7DirStatic = 11,
    ListeningTestFogMinus20DirStatic = 12,
    ListeningTestMinGainBelow1kHzDirStatic = 13
};

/$Listening test prog 6$/
const cap enum ListeningTestForHP6 20038
{
    ListeningTestFog = 0,
    ListeningTestWirelessAudio = 1,
    ListeningTestTelecoil = 2,
    ListeningTestAudioshoe = 3,
    ListeningTestMaxMpoAtt = 4,
    ListeningTestFogMinus7 = 5,
    ListeningTestFogMinus20 = 6,
    ListeningTestMinGainBelow1kHz = 7,
    ListeningTestNoiser = 8,
    ListeningTestFogDirStatic = 9,
    ListeningTestMaxMpoAttDirStatic = 10,
    ListeningTestFogMinus7DirStatic = 11,
    ListeningTestFogMinus20DirStatic = 12,
    ListeningTestMinGainBelow1kHzDirStatic = 13
};

/$OverrulingGroup2$/
const cap int OverrulingGroup2 20039
{0,65535};

/$CustomReceiver$/
const cap enum CustomReceiver 20040
{
    UNKNOWN = 0,
    NONE = 1,
    _4400 = 2,
    GD30 = 3,
    FEC = 4,
    HODT24 = 5,
    HODT15 = 6
};

/$Faceplate1$/
const cap int Faceplate1 20041
{0,65535};

/$Faceplate2$/
const cap int Faceplate2 20042
{0,65535};

/$Faceplate3$/
const cap int Faceplate3 20043
{0,65535};

/$Faceplate4$/
const cap int Faceplate4 20044
{0,65535};

/$Faceplate5$/
const cap int Faceplate5 20045
{0,65535};

/$UserControl$/
const cap enum UserControl 20046
{
    NoUserCtrl = 0,
    PushBtn = 1,
    RockerSwt = 2,
    PushBtnOrFlatCvr = 3,
    PushBtnOrRockerSwtOrFlatCvr = 4,
    PushBtnNonConfAndRockerSwtNonConf = 5,
    PushBtnConfAndRockerSwtNonConf = 6,
    PushBtnNonConfAndRockerSwtConf = 7
};

/$Number of hearing programs which shall be available in the Listening Test setting$/
const cap int NoOfProgramsForListeningTest 20047
{1,6};

/$Simulation$/
const cap enum Simulation 20048
{
    disable = 0,
    enable = 1
};

/$Insitu audiometry available
disable             Insitu audiometry not available
enable              Insitu audiometry available$/
const cap enum InsituAvailable 20049
{
    disable = 0, // Insitu audiometry not available
    enable = 1 // Insitu audiometry available
};

/$OverrulingGroup3$/
const cap int OverrulingGroup3 20050
{0,65535};

/$Default experience level$/
const cap enum DefExperienceLevel 20051
{
    New = 0,
    Experienced = 1
};

/$Default Hi Selection order. Used for Hi Selection page.$/
const cap int HISelectionOrder1 20052
{0,65535};

/$Allow AutoFit functionality for HI
No                  AutoFit not available
Yes                 AutoFit available$/
const cap enum AutoFitAvailable 20053
{
    No = 0, // AutoFit not available
    Yes = 1 // AutoFit available
};

/$Amplifier Type needed for order based preselection$/
const cap int AmplifierType 20054
{0,65535};

/$AACC Automatically Acclimatisation Level$/
const cap enum AACC 20055
{
    disable = 0,
    enable = 1
};

/$Rechargeable$/
const cap enum Rechargeable 20056
{
    disable = 0,
    enable = 1
};

/$BatterySize$/
const cap enum BatterySize 20057
{
    UNKNOWN = 0,
    _10 = 1,
    _312 = 2,
    _13 = 3,
    _675 = 4
};

/$To identification of SuperPower HIs$/
const cap enum SuperPowerHI 20058
{
    NoSuperPower = 0,
    SuperPower = 1
};

/$Activation and deactivation od Sound Balance Feature$/
const cap enum SoundBalanceAvailable 20059
{
    disable = 0,
    enable = 1
};

/$OverrulingGroup that shall only be used by Brands to diversify$/
const cap int OverrulingGroupBrand 20060
{0,65535};

/$Indicates the availability of the autofit optimization$/
const cap enum AutoFitOptimizationAvailable 20061
{
    disable = 0,
    enable = 1
};

/$Indicates the availability of a 3rd experience level$/
const cap enum SuperExperienceLevel 20062
{
    NotAvailable = 0,
    Available = 1
};

/$Indicates the availability of Single Dir Mic Feature$/
const cap enum SingleDirMic 20063
{
    NotAvailable = 0,
    Available = 1
};

/$OverrulingGroup to diversify on Shell, Reciever, GMax, WLS$/
const cap int recCurveGMax 20064
{0,65535};

/$OverrulingGroup to diversify on Shell- and Mic-Type$/
const cap int OverrulingGroupShellMic 20065
{0,65535};

/$the gender of the patient. it is 'neutral' if it is unknown$/
const pat enum Gender 30001 {
    neutral           = 0,
    male              = 1,
    female            = 2
};

/$age of patient in month$/
const pat int Age 30002
{0, 1440};

/$current client type$/
fit enum ClientType 30003 {
    Child             = 0,
    Adult             = 1,
    Undefined         = 2
};


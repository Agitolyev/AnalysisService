# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: AnalysisService.proto

import sys
_b=sys.version_info[0]<3 and (lambda x:x) or (lambda x:x.encode('latin1'))
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='AnalysisService.proto',
  package='com.github.agitolyev',
  syntax='proto3',
  serialized_options=None,
  serialized_pb=_b('\n\x15\x41nalysisService.proto\x12\x14\x63om.github.agitolyev\"\x1f\n\x0f\x41nalysisRequest\x12\x0c\n\x04text\x18\x01 \x01(\t\"\x1d\n\rAnalysisReply\x12\x0c\n\x04text\x18\x02 \x01(\t2j\n\x0f\x41nalysisService\x12W\n\x07\x41nalyze\x12%.com.github.agitolyev.AnalysisRequest\x1a#.com.github.agitolyev.AnalysisReply\"\x00\x62\x06proto3')
)




_ANALYSISREQUEST = _descriptor.Descriptor(
  name='AnalysisRequest',
  full_name='com.github.agitolyev.AnalysisRequest',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='text', full_name='com.github.agitolyev.AnalysisRequest.text', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=47,
  serialized_end=78,
)


_ANALYSISREPLY = _descriptor.Descriptor(
  name='AnalysisReply',
  full_name='com.github.agitolyev.AnalysisReply',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='text', full_name='com.github.agitolyev.AnalysisReply.text', index=0,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=80,
  serialized_end=109,
)

DESCRIPTOR.message_types_by_name['AnalysisRequest'] = _ANALYSISREQUEST
DESCRIPTOR.message_types_by_name['AnalysisReply'] = _ANALYSISREPLY
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

AnalysisRequest = _reflection.GeneratedProtocolMessageType('AnalysisRequest', (_message.Message,), {
  'DESCRIPTOR' : _ANALYSISREQUEST,
  '__module__' : 'AnalysisService_pb2'
  # @@protoc_insertion_point(class_scope:com.github.agitolyev.AnalysisRequest)
  })
_sym_db.RegisterMessage(AnalysisRequest)

AnalysisReply = _reflection.GeneratedProtocolMessageType('AnalysisReply', (_message.Message,), {
  'DESCRIPTOR' : _ANALYSISREPLY,
  '__module__' : 'AnalysisService_pb2'
  # @@protoc_insertion_point(class_scope:com.github.agitolyev.AnalysisReply)
  })
_sym_db.RegisterMessage(AnalysisReply)



_ANALYSISSERVICE = _descriptor.ServiceDescriptor(
  name='AnalysisService',
  full_name='com.github.agitolyev.AnalysisService',
  file=DESCRIPTOR,
  index=0,
  serialized_options=None,
  serialized_start=111,
  serialized_end=217,
  methods=[
  _descriptor.MethodDescriptor(
    name='Analyze',
    full_name='com.github.agitolyev.AnalysisService.Analyze',
    index=0,
    containing_service=None,
    input_type=_ANALYSISREQUEST,
    output_type=_ANALYSISREPLY,
    serialized_options=None,
  ),
])
_sym_db.RegisterServiceDescriptor(_ANALYSISSERVICE)

DESCRIPTOR.services_by_name['AnalysisService'] = _ANALYSISSERVICE

# @@protoc_insertion_point(module_scope)

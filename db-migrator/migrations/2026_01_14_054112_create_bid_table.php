<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('bid', function (Blueprint $table) {
            $table->id();
            $table->unsignedBigInteger('estimation_request_id')->index()->comment('고객 견적 의뢰 고유키');
            $table->unsignedInteger('price')->comment('금액');
            $table->unsignedInteger('duration')->comment('소요기간');
            $table->string('options', 300)->default('')->comment('기타사항');
            $table->dateTime('created_at')->index()->comment('생성일시');
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('bid');
    }
};
